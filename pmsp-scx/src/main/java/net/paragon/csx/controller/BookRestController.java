package net.paragon.csx.controller;

import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.paragon.controller.BaseRestController;
import net.paragon.csx.entity.Book;
import net.paragon.csx.service.BookService;
import net.paragon.framework.model.SearchParameter;
import net.paragon.utility.CommonConstants;
import net.paragon.utility.CommonUtility;

@RequestMapping(CommonConstants.REST_API + "book/")
@RestController
public class BookRestController extends BaseRestController<Book>{
	private static final long serialVersionUID = 5744267626610556992L;

	private final static String CACHE_OBJECTS_KEY = "cached.books";

	@Inject
	private BookService businessService;

	@RequestMapping(path = "/list", method = RequestMethod.GET)
	public List<Book> fetchBusinessObjects(HttpServletRequest request, HttpServletResponse response, Model model) {
		log.info("RestController::Come to enterprise data listing ...>>>>>>");
		List<Book> results = null;
		Object cachedValue = super.cacheGet(CACHE_OBJECTS_KEY);
		PageRequest pageRequest = null;
		SearchParameter searchParameter = null;
		Page<Book> objects = null;
		if (CommonUtility.isNotEmpty(cachedValue)){
			results = (List<Book>)cachedValue;
		} else {
			pageRequest = PageRequest.of(0, 500, Sort.Direction.ASC, "id");
			searchParameter = SearchParameter.builder()
					.pageable(pageRequest)
					.build();
			objects = businessService.getObjects(searchParameter);
			results = objects.getContent();
			super.cachePut(CACHE_OBJECTS_KEY, results);
		}
		log.info("Book data is loaded. >>>>>>");

		return results;
	}
	
	@RequestMapping(value = "/get/{name}", method = RequestMethod.GET)
	public @ResponseBody Book fetchByIsbn(HttpServletRequest request, @PathVariable("isbn") String isbn) {
		Book fetchedObject = null;
		try {
			fetchedObject = this.businessService.getByIsbn(isbn);
			if (null == fetchedObject) {
				fetchedObject = new Book();
				fetchedObject.setTitle("Sách này là Khộng Tưởng. Chưa có trong hệ thống. Kết quả trả vể từ RESTFul. ");
				fetchedObject.setId(new Long(-1));
			}
			System.out.println("Found book: [" + fetchedObject + "]");
		} catch (Exception e) {
			log.error(e);	
		}
		return fetchedObject;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> add(HttpServletRequest request, @RequestBody Book book) {
		ResponseEntity<?> responseEntity = null;
		try {
			//book.setPhoto(ImageUtil.smallNoImage());
			this.businessService.saveOrUpdate(book);
			URI projectUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(book.getId()).toUri();

			responseEntity = ResponseEntity.created(projectUri).build();
		} catch (Exception e) {
			log.error(e);		
			responseEntity = ResponseEntity.noContent().build();
		}
		return responseEntity;
	}
}
