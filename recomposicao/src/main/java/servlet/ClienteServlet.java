package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;


@WebServlet(value = "/cliente")
public class ClienteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET");
        String pathInfo = req.getServletPath();
        if (pathInfo.equals("/edit"))
            doPut(req, resp);
        else if (pathInfo.equals("/delete"))
            doDelete(req, resp);
        else
            listar(req, resp);
    }

    protected void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Cliente> clientes = session.createQuery("from Cliente", Cliente.class).list();

        req.setAttribute("clientes", clientes);
        RequestDispatcher dispatcher = req.getRequestDispatcher("cliente.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String idParam = req.getParameter("id");
        if (!idParam.isEmpty()) {
            long id = Long.parseLong(idParam);
            Cliente cliente = session.get(Cliente.class, id);
            cliente.setNome(req.getParameter("nome"));
            cliente.setCpf(req.getParameter("cpf"));
            session.merge(cliente);
        } else {
            Cliente cliente = new Cliente(req.getParameter("nome"), req.getParameter("cpf"));
            session.persist(cliente);
        }

        session.getTransaction().commit();
        listar(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("PUT");
        Session session = HibernateUtil.getSessionFactory().openSession();

        long id = Long.parseLong(req.getParameter("id"));
        Cliente cliente = session.get(Cliente.class, id);

        req.setAttribute("cliente", cliente);
        req.setAttribute("id", id);

        List<Cliente> clientes = session.createQuery("from Cliente", Cliente.class).list();

        req.setAttribute("clientes", clientes);
        RequestDispatcher dispatcher = req.getRequestDispatcher("cliente.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DELETE");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        long id = Long.parseLong(req.getParameter("id"));
        session.remove(session.get(Cliente.class, id));

        session.getTransaction().commit();
        listar(req, resp);
    }
}