package calaru.util.pager;

import java.util.List;

public final class Page<T> {

	public final List<T> items;
	public final long total;
	
	public Page(List<T> items, long total) {
		super();
		this.items = items;
		this.total = total;
	}
}
