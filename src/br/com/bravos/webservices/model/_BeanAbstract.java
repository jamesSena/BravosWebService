package br.com.bravos.webservices.model;

/**
 * @author JamessonSena
 *
 */
public class _BeanAbstract {
	private boolean success = true;
	private String detail ="sucesso", reason = "1";

	/**
	 * @param success
	 * @param detail
	 * @param reason
	 */
	public _BeanAbstract(boolean success, String detail, String reason) {
		super();
		this.success = success;
		this.detail = detail;
		this.reason = reason;
	}

	
	/**
	 * 
	 */
	public _BeanAbstract() {
	}


	/**
	 * @return transação realizada com sucesso
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success - se a transação foi realizada com sucesso
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return o detalhe da transação
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * @param detail
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * @return a razão do erro/sucesso da transação
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("_BeanAbstract [success=%s, detail=%s, reason=%s]", success, detail, reason);
	}



}
