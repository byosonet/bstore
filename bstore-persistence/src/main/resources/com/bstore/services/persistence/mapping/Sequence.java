// default package
// Generated 31-may-2016 0:05:40 by Hibernate Tools 4.3.1

import java.math.BigDecimal;

/**
 * Sequence generated by hbm2java
 */
public class Sequence implements java.io.Serializable {

	private String seqName;
	private BigDecimal seqCount;

	public Sequence() {
	}

	public Sequence(String seqName) {
		this.seqName = seqName;
	}

	public Sequence(String seqName, BigDecimal seqCount) {
		this.seqName = seqName;
		this.seqCount = seqCount;
	}

	public String getSeqName() {
		return this.seqName;
	}

	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}

	public BigDecimal getSeqCount() {
		return this.seqCount;
	}

	public void setSeqCount(BigDecimal seqCount) {
		this.seqCount = seqCount;
	}

}