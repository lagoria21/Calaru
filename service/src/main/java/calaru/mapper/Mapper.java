package calaru.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface Mapper<T, D> {
	
	D entityToDto(T entity);
	T dtoToEntity(D dto);
	default List<D> entitiesToDtos(List<T> s) {
		return copy(s, this::entityToDto);
	}
	default List<T> dtosToEntities(List<D> s) {
		return copy(s, this::dtoToEntity);
	}
	static <A,B> List<B> copy(List<A> as, Function<A, B> f) {
		return as.stream().map(f).collect(Collectors.toList());
	}

}
