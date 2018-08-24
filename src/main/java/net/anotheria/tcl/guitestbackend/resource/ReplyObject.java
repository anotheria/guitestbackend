package net.anotheria.tcl.guitestbackend.resource;

/**
 * TODO comment this class
 *
 * @author lrosenberg
 * @since 24.08.18 14:27
 */
public class ReplyObject {
	public static ReplyObject INSTANCE = new ReplyObject();

	/**
	 * Just to fill up request, we may need it in the future.
	 */
	private boolean success = true;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}
