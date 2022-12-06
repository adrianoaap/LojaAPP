package testes;

import dao.ProdutoDAO;
import model.Categoria;
import model.Produto;
import util.JPA;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ManyToOneProdutoCategoria {

    public static void main(String[] args) {

        EntityManager entityManager = JPA.getEntityManager();

        LocalDate dataManipulacao = LocalDate.now();

        Categoria informatica = entityManager.find(Categoria.class, 1);
        Categoria eletro = entityManager.find(Categoria.class, 2);
        Categoria gamer = entityManager.find(Categoria.class, 3);

        entityManager.getTransaction().begin();

        Produto dell = entityManager.find(Produto.class, 1);
        dell.setCategoria(informatica);
        dell.setDataCadastro(dataManipulacao.plusDays(5));
        entityManager.persist(dell);

        Produto mac = entityManager.find(Produto.class, 2);
        mac.setCategoria(informatica);
        mac.setDataCadastro(dataManipulacao.plusDays(10));
        entityManager.persist(mac);

        Produto tv = entityManager.find(Produto.class, 3);
        tv.setCategoria(eletro);
        tv.setDataCadastro(dataManipulacao.plusDays(8));
        entityManager.persist(tv);

        Produto lavar = entityManager.find(Produto.class, 4);
        lavar.setCategoria(eletro);
        lavar.setDataCadastro(dataManipulacao.plusWeeks(5));
        entityManager.persist(lavar);

        Produto geladeira = entityManager.find(Produto.class, 5);
        geladeira.setCategoria(eletro);
        geladeira.setDataCadastro(dataManipulacao.plusWeeks(1));
        entityManager.persist(geladeira);

        Produto nitendo = entityManager.find(Produto.class, 6);
        nitendo.setCategoria(gamer);
        nitendo.setDataCadastro(dataManipulacao.plusDays(15));
        entityManager.persist(nitendo);

        Produto playstation = entityManager.find(Produto.class, 7);
        playstation.setCategoria(gamer);
        playstation.setDataCadastro(dataManipulacao.plusDays(3));
        entityManager.persist(playstation);

        entityManager.getTransaction().commit();

        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);

        System.out.println(" ### BUSCAR TODOS OS PRODUTOS ###");
        List<Produto> buscarTodosProdutos = produtoDAO.buscarTodos();
        System.out.println(" ######## lista produtos por ordem decrecente  ########");
        buscarTodosProdutos.forEach(p -> System.out.println(p.getNome() + " - R$ " + p.getPreco()));

        System.out.println(" ### BUSCAR POR NOME OS PRODUTOS ###");
        List<Produto> buscarNome = produtoDAO.buscarPorNome(geladeira.getNome());
        System.out.println(" #### Único Produto ####");
        System.out.println(" Produto.: " + buscarNome);

        System.out.println(" ### BUSCAR POR CATEGORIA ###");
        List<Produto> buscarCategoria = produtoDAO.buscarPorCategoria(informatica.getNome());
        System.out.println(" #### Lista Categoria ####");
        buscarCategoria.forEach(p -> System.out.println(p.getNome() + " - R$ " + p.getPreco()));

        System.out.println(" ### BUSCAR POR CATEGORIA ###");
        BigDecimal pesquisarProduto = produtoDAO.buscarPrecoProdutoComNome(dell.getNome());
        System.out.println(" #### Buscar preco ####");
        System.out.println(pesquisarProduto);

        entityManager.close();

    }

}
