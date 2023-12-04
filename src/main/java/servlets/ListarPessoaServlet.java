package servlets;

import java.io.IOException;
import java.util.List;

import org.hibernate.Session;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Pessoa;
import util.HibernateUtil;

@WebServlet(value = "/ListarPessoa")
public class ListarPessoaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Pessoa> pessoas = session.createQuery("from Pessoa ", Pessoa.class).list();

        req.setAttribute("pessoas", pessoas);
        RequestDispatcher dispatcher = req.getRequestDispatcher("listarPessoa.jsp");
        dispatcher.forward(req, resp);
    }
}
