/**
 * 
 */
package net.paragon.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.paragon.domain.RestErrorInfo;
import net.paragon.exceptions.DataFormatException;
import net.paragon.exceptions.ResourceNotFoundException;

/**
 * @author bqduc
 *
 */
public abstract class BaseRestController<T> extends RootController {
  /**
	 * 
	 */
	private static final long serialVersionUID = -2890610255967939983L;
	protected static final String  DEFAULT_PAGE_SIZE = "100";
  protected static final String DEFAULT_PAGE_NUM = "0";

	@RequestMapping(
			value = "/create", 
			method = RequestMethod.POST, 
			consumes = { "application/json", "application/xml" }, 
			produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(
			value = "Create a aquafeed resource.", 
			notes = "Returns the URL of the new resource in the Location header.")
	public void createBusinessObject(
			@RequestBody T clientObject, 
			HttpServletRequest request, 
			HttpServletResponse response) {
		this.doCreateBusinessObject(clientObject);
	}

	@RequestMapping(value = "/fetch", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(
			value = "Get a paginated list of all aquafeeds.", 
			notes = "The list is paginated. You can provide a page number (default 0) and a page size (default 100)")
	public @ResponseBody Page<T> fetchBusinessObjects(
			@ApiParam(value = "The page number (zero-based)", required = true) 
			@RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
			@ApiParam(value = "Tha page size", required = true) 
			@RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
			HttpServletRequest request, HttpServletResponse response) {
		return this.doFetchBusinessObjects(page, size);
	}

	@RequestMapping(value = "/fetch/{id}", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "Get a single aquafeed.", notes = "You have to provide a valid aquafeed ID.")
	public @ResponseBody T fetchBusinessObject(@ApiParam(value = "The ID of the aquafeed.", required = true) @PathVariable("id") Long id,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		return this.doFetchBusinessObject(id);
	}

	@RequestMapping(
			value = "/update/{id}", 
			method = RequestMethod.PUT, 
			consumes = { "application/json", "application/xml" }, 
			produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Update a aquafeed resource.", notes = "You have to provide a valid aquafeed ID in the URL and in the payload. The ID attribute can not be updated.")
	public void updateBusinessObject(@ApiParam(value = "The ID of the existing aquafeed resource.", required = true) @PathVariable("id") Long id,
			@RequestBody T updatedClientObject, 
			HttpServletRequest request, 
			HttpServletResponse response) {
		this.doUpdateBusinessObject(updatedClientObject);
	}

	// TODO: @ApiImplicitParams, @ApiResponses
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete a aquafeed resource.", notes = "You have to provide a valid aquafeed ID in the URL. Once deleted the resource can not be recovered.")
	public void deleteBusinessObject(@ApiParam(value = "The ID of the existing aquafeed resource.", required = true) @PathVariable("id") Long id,
			HttpServletRequest request, HttpServletResponse response) {
		this.doDeleteBusinessObject(id);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(DataFormatException.class)
  public
  @ResponseBody
  RestErrorInfo handleDataStoreException(DataFormatException ex, WebRequest request, HttpServletResponse response) {
      log.info("Converting Data Store exception to RestResponse : " + ex.getMessage());

      return new RestErrorInfo(ex, "You messed up.");
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ResourceNotFoundException.class)
  public
  @ResponseBody
  RestErrorInfo handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request, HttpServletResponse response) {
      log.info("ResourceNotFoundException handler:" + ex.getMessage());

      return new RestErrorInfo(ex, "Sorry I couldn't find it.");
  }

  //TODO: replace with exception mapping
  public static <T> T checkResourceFound(final T resource) {
      if (resource == null) {
          throw new ResourceNotFoundException("resource not found");
      }
      return resource;
  }

	protected void doUpdateBusinessObject(T updatedClientObject) {
	}

	protected Page<T> doFetchBusinessObjects(Integer page, Integer size) {
		return null;
	}

	protected T doFetchBusinessObject(Long id) {
		return null;
	}

	protected void doDeleteBusinessObject(Long id) {
	}

	protected void doCreateBusinessObject(T businessObject) {
	}
}
