package cs636.music.presentation.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cs636.music.domain.Product;
import cs636.music.domain.Track;
import cs636.music.domain.User;
import cs636.music.service.CatalogServiceAPI;
import cs636.music.service.SalesServiceAPI;
import cs636.music.service.ServiceException;

public class DownloadController implements Controller {
	private SalesServiceAPI salesService;
	private CatalogServiceAPI catalogService;

	public DownloadController(SalesServiceAPI salesService, CatalogServiceAPI catalogS ) {
		this.salesService = salesService;
		this.catalogService = catalogS;
	}

	public String handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		System.out.println("DownloadController: starting");
		HttpSession session = request.getSession();
		UserBean userBean = (UserBean) session.getAttribute("user");
		User user = userBean.getUser();
		Product product = userBean.getProduct();
		Integer trackNumber = Integer.parseInt((String) request.getParameter("trackNum"));
		Track track = null;
		try {
			track = product.findTrackByNumber(trackNumber);
			catalogService.addDownload(user.getEmailAddress(), track);
		} catch (ServiceException e) {
			System.out.println("DownloadController: " + e);
			throw new ServletException(e);
		}
		String downloadURL = "/sound/" + product.getCode() + "/" + track.getSampleFilename();

		return downloadURL;
	}
}
