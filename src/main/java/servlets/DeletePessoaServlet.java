package servlets;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Pessoa;
import util.HibernateUtil;

@WebServlet("/DeletePessoa")
public class DeletePessoaServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		if (id != null && !id.isEmpty()) {
			try {
				Long pessoaId = Long.parseLong(id);

				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction transaction = session.beginTransaction();

				Pessoa pessoa = session.get(Pessoa.class, pessoaId);

				if (pessoa != null) {
					session.delete(pessoa);
					transaction.commit();
				}

				session.close();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		response.sendRedirect("ListarPessoa");
	}
}
