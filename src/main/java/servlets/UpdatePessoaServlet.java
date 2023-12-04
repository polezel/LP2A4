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

@WebServlet("/UpdatePessoa")
public class UpdatePessoaServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParam = request.getParameter("id");

		if (idParam != null && !idParam.isEmpty()) {
			try {
				Long pessoaId = Long.parseLong(idParam);

				Session session = HibernateUtil.getSessionFactory().openSession();
				Pessoa pessoa = session.get(Pessoa.class, pessoaId);
				session.close();

				if (pessoa != null) {
					request.setAttribute("pessoa", pessoa);
					request.getRequestDispatcher("updatePessoa.jsp").forward(request, response);
					return;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}

		response.sendRedirect("ListarPessoa");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idParam = request.getParameter("id");
		String novoNome = request.getParameter("novoNome");

		if (idParam != null && !idParam.isEmpty() && novoNome != null && !novoNome.isEmpty()) {
			try {
				Long pessoaId = Long.parseLong(idParam);

				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction transaction = session.beginTransaction();

				Pessoa pessoa = session.get(Pessoa.class, pessoaId);

				if (pessoa != null) {
					pessoa.setNome(novoNome);

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
