package dao;

import model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProdutoDAO {

    private EntityManager entityManager;

    public ProdutoDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void cadastrar(Produto produto) {
        this.entityManager.persist(produto);
    }

    public void atualizar(Produto produto) {
        this.entityManager.merge(produto);
    }

    public List<Produto> buscarTodos() {
        String jpql = "SELECT p FROM Produto p ORDER BY p.preco DESC";
        return entityManager.createQuery(jpql).getResultList();
    }

    public List<Produto> buscarPorNome(String nomeProduto) {
        String jpql = "SELECT p FROM Produto p WHERE p.nome =:nome";
        return entityManager.createQuery(jpql).setParameter("nome", nomeProduto)
                .getResultList();
    }

    public List<Produto> buscarPorCategoria(String nomeProduto) {
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome =:nome";
        return entityManager.createQuery(jpql).setParameter("nome", nomeProduto)
                .getResultList();
    }

    public BigDecimal buscarPrecoProdutoComNome(String nomeProduto) {
        String jpql = "SELECT p.preco FROM Produto p WHERE p.nome =:nome";
        return entityManager.createQuery(jpql, BigDecimal.class).setParameter("nome", nomeProduto)
                .getSingleResult();
    }

    public List<Produto> buscarPorParametros(String nome, BigDecimal preco, LocalDate dataCadastro) {

        String jpql = "SELECT p FROM Produto p WHERE 1=1 ";

        if (nome != null && !nome.trim().isEmpty()) {
            jpql = " AND p.nome =:nome ";
        }
        if (preco != null) {
            jpql = " AND p.preco =:preco ";
        }
        if (dataCadastro != null) {
            jpql = " AND p.dataCadastro =:dataCadastro ";
        }

        TypedQuery<Produto> query = entityManager.createQuery(jpql, Produto.class);

        if (nome != null && !nome.trim().isEmpty()) {
            query.setParameter("nome", nome);
        }
        if (preco != null) {
            query.setParameter("preco", preco);
        }
        if (dataCadastro != null) {
            query.setParameter("dataCadastro", dataCadastro);
        }

        return query.getResultList();

    }
}
