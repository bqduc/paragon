package net.paragon.csx.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Digits;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import net.paragon.framework.entity.BizObjectBase;
import net.paragon.global.GlobalConstants;

/**
 * A Book.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "csx_book")
public class Book extends BizObjectBase{
	private static final long serialVersionUID = 1L;

	@Version
	@Column(name = "version")
	private Integer version;

	@Column(name = "isbn", length=GlobalConstants.SIZE_SERIAL)
	private String isbn;

	@Column(name = "isbno", length=GlobalConstants.SIZE_SERIAL)
	private String isbno;

	@Column(name = "isbne", length=GlobalConstants.SIZE_SERIAL)
	private String isbne;

	@Column(name = "isbn13", length=GlobalConstants.SIZE_SERIAL)
	private String isbn13;

	@Column(name = "isbn13o", length=GlobalConstants.SIZE_SERIAL)
	private String isbn13o;

	@Column(name = "isbn13e", length=GlobalConstants.SIZE_SERIAL)
	private String isbn13e;

	@Column(name = "title", nullable = false, length=200)
	private String title;

	@Column(name = "author", nullable = false, length=200)
	private String author;

	@Column(name = "main_subject_category", length=50)
	private String mainSubjectCategory;

	@Column(name = "specialized_subject_area", length=50)
	private String specializedSubjectArea;

	@Column(name = "oclc", length=12)
	private String oclc;

	@Digits(integer=12, fraction=2)
	@Column(name = "online_price")
	private BigDecimal onlinePrice;

	@Column(name = "online_lib_url", length=200)
	private String onlineLibUrl;

  @Column(name="online_availability_date")
	@DateTimeFormat(iso = ISO.DATE)
  private Date onlineAvailabilityDate;
	
  @Column(name="print_publication_date")
	@DateTimeFormat(iso = ISO.DATE)
  private Date printPublicationDate;

	@Digits(integer=12, fraction=2)
	@Column(name = "print_price")
	private BigDecimal printPrice;

	@Column(name = "content_language", length=50)
	private String contentLanguage;

	@Column(name = "page_count")
	private Short pageCount;

	@Column(name = "publisher", length=150)
	private String publisher;

	@Column(name = "sales_model_onetime", length=50)
	private String salesModelOnetime;

	@Column(name = "sales_model_subscription", length=50)
	private String salesModelsubscription;
	
	@Column(name = "online_series", length=12)
	private String onlineSeries;

	@Column(name = "online_series_description", length=200)
	private String onlineSeriesDescription;

	@Column(name = "online_backvolume", length=50)
	private String onlineBackvolume;
	
	@Column(name = "business_group", length=50)
	private String businessGroup;
	
	@Column(name = "Imprint", length=80)
	private String Imprint;

	@Column(name = "date_of_publication")
	private String dateOfPublication;

	@Column(name = "description", columnDefinition="TEXT")
	private String description;

	@Basic(fetch = FetchType.LAZY)
	@Column(name = "photo", columnDefinition="TEXT")
	private String photo;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Book book = (Book) o;

		if (!Objects.equals(getId(), book.getId()))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getIsbno() {
		return isbno;
	}

	public void setIsbno(String isbno) {
		this.isbno = isbno;
	}

	public String getIsbne() {
		return isbne;
	}

	public void setIsbne(String isbne) {
		this.isbne = isbne;
	}

	public String getIsbn13() {
		return isbn13;
	}

	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}

	public String getIsbn13o() {
		return isbn13o;
	}

	public void setIsbn13o(String isbn13o) {
		this.isbn13o = isbn13o;
	}

	public String getIsbn13e() {
		return isbn13e;
	}

	public void setIsbn13e(String isbn13e) {
		this.isbn13e = isbn13e;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getMainSubjectCategory() {
		return mainSubjectCategory;
	}

	public void setMainSubjectCategory(String mainSubjectCategory) {
		this.mainSubjectCategory = mainSubjectCategory;
	}

	public String getSpecializedSubjectArea() {
		return specializedSubjectArea;
	}

	public void setSpecializedSubjectArea(String specializedSubjectArea) {
		this.specializedSubjectArea = specializedSubjectArea;
	}

	public String getOclc() {
		return oclc;
	}

	public void setOclc(String oclc) {
		this.oclc = oclc;
	}

	public BigDecimal getOnlinePrice() {
		return onlinePrice;
	}

	public void setOnlinePrice(BigDecimal onlinePrice) {
		this.onlinePrice = onlinePrice;
	}

	public String getOnlineLibUrl() {
		return onlineLibUrl;
	}

	public void setOnlineLibUrl(String onlineLibUrl) {
		this.onlineLibUrl = onlineLibUrl;
	}

	public Date getOnlineAvailabilityDate() {
		return onlineAvailabilityDate;
	}

	public void setOnlineAvailabilityDate(Date onlineAvailabilityDate) {
		this.onlineAvailabilityDate = onlineAvailabilityDate;
	}

	public Date getPrintPublicationDate() {
		return printPublicationDate;
	}

	public void setPrintPublicationDate(Date printPublicationDate) {
		this.printPublicationDate = printPublicationDate;
	}

	public BigDecimal getPrintPrice() {
		return printPrice;
	}

	public void setPrintPrice(BigDecimal printPrice) {
		this.printPrice = printPrice;
	}

	public String getContentLanguage() {
		return contentLanguage;
	}

	public void setContentLanguage(String contentLanguage) {
		this.contentLanguage = contentLanguage;
	}

	public Short getPageCount() {
		return pageCount;
	}

	public void setPageCount(Short pageCount) {
		this.pageCount = pageCount;
	}

	public String getSalesModelOnetime() {
		return salesModelOnetime;
	}

	public void setSalesModelOnetime(String salesModelOnetime) {
		this.salesModelOnetime = salesModelOnetime;
	}

	public String getSalesModelsubscription() {
		return salesModelsubscription;
	}

	public void setSalesModelsubscription(String salesModelsubscription) {
		this.salesModelsubscription = salesModelsubscription;
	}

	public String getOnlineSeries() {
		return onlineSeries;
	}

	public void setOnlineSeries(String onlineSeries) {
		this.onlineSeries = onlineSeries;
	}

	public String getOnlineSeriesDescription() {
		return onlineSeriesDescription;
	}

	public void setOnlineSeriesDescription(String onlineSeriesDescription) {
		this.onlineSeriesDescription = onlineSeriesDescription;
	}

	public String getOnlineBackvolume() {
		return onlineBackvolume;
	}

	public void setOnlineBackvolume(String onlineBackvolume) {
		this.onlineBackvolume = onlineBackvolume;
	}

	public String getBusinessGroup() {
		return businessGroup;
	}

	public void setBusinessGroup(String businessGroup) {
		this.businessGroup = businessGroup;
	}

	public String getImprint() {
		return Imprint;
	}

	public void setImprint(String imprint) {
		Imprint = imprint;
	}

	public String getDateOfPublication() {
		return dateOfPublication;
	}

	public void setDateOfPublication(String dateOfPublication) {
		this.dateOfPublication = dateOfPublication;
	}
}
