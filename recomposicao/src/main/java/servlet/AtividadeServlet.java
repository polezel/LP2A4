package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atividade;
import org.hibernate.Session;

import java.io.IOException;
import java.util.List;

@WebServlet("/atividade")
public class AtividadeServlet extends HttpServlet {
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

        List<Atividade> atividades = session.createQuery("from Atividade", Atividade.class).list();

        req.setAttribute("atividades", atividades);
        RequestDispatcher dispatcher = req.getRequestDispatcher("atividade.jsp");
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
            Atividade atividade = session.get(Atividade.class, id);
            atividade.setNome(req.getParameter("nome"));
            atividade.setData(req.getParameter("date"));
            atividade.setHora(req.getParameter("hora"));
            atividade.setDescricao(req.getParameter("descricao"));
            session.merge(atividade);
        } else {
            Atividade atividade = new Atividade();
            atividade.setNome(req.getParameter("nome"));
            atividade.setData(req.getParameter("date"));
            atividade.setHora(req.getParameter("hora"));
            atividade.setDescricao(req.getParameter("descricao"));
            session.persist(atividade);
        }

        session.getTransaction().commit();
        listar(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("PUT");
        Session session = HibernateUtil.getSessionFactory().openSession();

        long id = Long.parseLong(req.getParameter("id"));
        Atividade atividade = session.get(Atividade.class, id);

        req.setAttribute("atividade", atividade);
        req.setAttribute("id", id);

        List<Atividade> atividades = session.createQuery("from Atividade", Atividade.class).list();

        req.setAttribute("atividades", atividades);
        RequestDispatcher dispatcher = req.getRequestDispatcher("atividade.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DELETE");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        long id = Long.parseLong(req.getParameter("id"));
        session.remove(session.get(Atividade.class, id));

        session.getTransaction().commit();
        listar(req, resp);
    }
}
