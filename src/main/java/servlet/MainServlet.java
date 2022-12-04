package servlet;

import com.google.gson.Gson;
import dao.FactoryDao;
import dao.ProductDao;
import model.Factory;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private static FactoryDao factoryDao = new FactoryDao();
    private static ProductDao productDao = new ProductDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String json = gson.toJson(productDao.readAll());
        resp.setContentType("text/plain");
        resp.getWriter().println(json);
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productName = req.getParameter("productName");
        String companyName = req.getParameter("factoryName");
        Integer count = Integer.valueOf(req.getParameter("count"));

        Factory factory = factoryDao.read(companyName);
        if (factory == null){
            String rand = String.valueOf((Math.random() * (100 - 1)) + 1);
            String address = "address" + rand;
            factoryDao.create(new Factory(companyName, address));
        }

        productDao.create(new Product(0, productName, companyName, count));
        resp.setStatus(200);
    }
}
