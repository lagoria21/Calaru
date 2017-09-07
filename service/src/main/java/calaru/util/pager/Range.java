package calaru.util.pager;

public class Range {
	public final int start;
	public final int end;

	public Range(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public static Range fromString(String strRange) {
		int pos = strRange.indexOf('-');
		if (pos > 0) {
			int start = Integer.parseInt(strRange.substring(0, pos));
			int end = Integer.parseInt(strRange.substring(pos + 1));
			return new Range(start, end);
		} else {
			throw new IllegalStateException();
		}
	}

}
