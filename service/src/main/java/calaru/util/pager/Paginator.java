package calaru.util.pager;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementa paginacion para servir a la directiva angular-paginate-anything.
 * 
 * @author gpollitzer
 */
@Repository
public class Paginator {
	@Autowired
	private EntityManager em;

	public <E, D> ResponseEntity<List<D>> buildPage(Class<E> targetEntity,
			String strRange, Function<E, D> mapper, BiFunction<CriteriaBuilder, Root<E>, Predicate> predicateBuilder) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Range-Unit", "items");
		Range range = Range.fromString(strRange);
		Page<D> page = this.findAll(targetEntity, predicateBuilder, mapper, range.start, range.end);
		final ResponseEntity<List<D>> re;
		if (!page.items.isEmpty()) {
			headers.add("Content-Range", buildContentRange(page, range));
			re = new ResponseEntity<List<D>>(page.items, headers, HttpStatus.PARTIAL_CONTENT);
		} else {
			headers.add("Content-Range", "*/0");
			re = new ResponseEntity<List<D>>(page.items, headers, HttpStatus.NO_CONTENT);
		}
		return re;
	}
	
	public <E, D> ResponseEntity<List<D>> buildPage(Class<E> targetEntity,
			String strRange, Function<E, D> mapper) {
		return this.buildPage(targetEntity, strRange, mapper, (CriteriaBuilder cb, Root<E> r) -> cb.conjunction());
	}

	private <D> String buildContentRange(Page<D> page, Range range) {
		return String.valueOf(range.start) + '-' + String.valueOf(range.end) + '/' + String.valueOf(page.total);
	}

	private <T, D> Page<D> findAll(Class<T> targetEntity,
			BiFunction<CriteriaBuilder, Root<T>, Predicate> predicateBuilder, Function<T, D> mapper, int start,
			int end) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		final List<T> list;
		{
			CriteriaQuery<T> cq = cb.createQuery(targetEntity);
			if(predicateBuilder != null)
				cq.where(predicateBuilder.apply(cb, cq.from(targetEntity)));
			TypedQuery<T> q = em.createQuery(cq);
			q.setFirstResult(start);
			q.setMaxResults(end - start + 1);
			list = q.getResultList();
		}
		final Long count;
		{
			CriteriaQuery<Long> cqc = cb.createQuery(Long.class);
			Root<T> root = cqc.from(targetEntity);
			cqc.select(cb.count(root));
			cqc.where(predicateBuilder.apply(cb, root));
			count = em.createQuery(cqc).getSingleResult();
		}
		Stream<D> listD = list.stream().map(mapper);
		return new Page<D>(listD.collect(Collectors.toList()), count);
	}

	@Transactional
	public <T, K> int deletePredicateExcludingIds(Class<T> targetEntity,
			BiFunction<CriteriaBuilder, Root<T>, Predicate> predicateBuilder, List<Long> ids,
			SingularAttribute<T, K> idField) {
		// equivale al JPAQ "delete entity where {predicate} and entity.id NOT
		// IN (:ids)"
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<T> cd = cb.createCriteriaDelete(targetEntity);
		Root<T> root = cd.from(targetEntity);
		cd.where(predicateBuilder.apply(cb, root), cb.not(root.get(idField).in(ids)));
		Query q = em.createQuery(cd);
		return q.executeUpdate();
	}

	@Transactional
	public <T, K> int deleteIds(Class<T> targetEntity, List<Long> ids, SingularAttribute<T, K> idField) {
		// equivale a "delete entity where entity.id IN (:ids)"
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaDelete<T> cd = cb.createCriteriaDelete(targetEntity);
		Root<T> root = cd.from(targetEntity);
		cd.where(root.get(idField).in(ids));
		Query q = em.createQuery(cd);
		return q.executeUpdate();
	}

}
