package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atividade;
import model.Cliente;
import model.Ingresso;
import org.hibernate.Session;

import java.io.IOException;

@WebServlet("/ingresso")
public class IngressoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");

        String clienteIdParam = req.getParameter("clienteId");
        String atividadeIdParam = req.getParameter("atividadeId");

        if (clienteIdParam != null && atividadeIdParam != null && !clienteIdParam.isEmpty() && !atividadeIdParam.isEmpty()) {
            long clienteId = Long.parseLong(clienteIdParam);
            long atividadeId = Long.parseLong(atividadeIdParam);

            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            try {
                Cliente cliente = session.get(Cliente.class, clienteId);
                Atividade atividade = session.get(Atividade.class, atividadeId);

                if (cliente != null && atividade != null) {
                    Ingresso ingresso = new Ingresso();
                    ingresso.setCliente(cliente);
                    ingresso.setAtividade(atividade);

                    session.persist(ingresso);

                    session.getTransaction().commit();
                    resp.sendRedirect("sucesso.jsp");
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                session.getTransaction().rollback();
                session.close();
            }
        }

        resp.sendRedirect("erro.jsp");
    }
}