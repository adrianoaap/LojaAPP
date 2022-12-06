package testes;

import dao.PedidoDAO;
import dao.ProdutoDAO;
import model.*;
import util.JPA;
import vo.RelatorioClientesVo;
import vo.RelatorioVendasVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ManyToManyPedidoClienteItemPedido {

    public static void main(String[] args) {

        EntityManager entityManager = JPA.getEntityManager();

        LocalDate dataManipulacao = LocalDate.now();

        Categoria informatica = entityManager.find(Categoria.class, 1);
        Categoria eletro = entityManager.find(Categoria.class, 2);
        Categoria gamer = entityManager.find(Categoria.class, 3);

        Cliente lufy = entityManager.find(Cliente.class, 1);
        Cliente sanjy = entityManager.find(Cliente.class, 2);
        Cliente zoro = entityManager.find(Cliente.class, 3);
        Cliente nami = entityManager.find(Cliente.class, 4);
        Cliente usopp = entityManager.find(Cliente.class, 5);

        Produto dell = entityManager.find(Produto.class, 1);
        dell.setDataCadastro(dataManipulacao.plusDays(5));
        dell.setCategoria(informatica);

        Produto macbook = entityManager.find(Produto.class, 2);
        macbook.setDataCadastro(dataManipulacao.plusDays(10));
        macbook.setCategoria(informatica);

        Produto tv = entityManager.find(Produto.class, 3);
        tv.setDataCadastro(dataManipulacao.plusDays(8));
        tv.setCategoria(informatica);

        Produto lavadeira = entityManager.find(Produto.class, 4);
        lavadeira.setDataCadastro(dataManipulacao.plusWeeks(5));
        lavadeira.setCategoria(eletro);

        Produto geladeira = entityManager.find(Produto.class, 5);
        geladeira.setDataCadastro(dataManipulacao.plusWeeks(1));
        geladeira.setCategoria(eletro);

        Produto nitendo = entityManager.find(Produto.class, 6);
        nitendo.setDataCadastro(dataManipulacao.plusDays(15));
        nitendo.setCategoria(gamer);

        Produto ps5 = entityManager.find(Produto.class, 7);
        ps5.setDataCadastro(dataManipulacao.plusDays(3));
        ps5.setCategoria(gamer);

        entityManager.getTransaction().begin();

        Pedido pedidoLuffy = new Pedido();
        pedidoLuffy.setCliente(lufy);
        pedidoLuffy.setData(dell.getDataCadastro());
        pedidoLuffy.adcionarItem(new ItemPedido(1, pedidoLuffy, dell));
        pedidoLuffy.adcionarItem(new ItemPedido(2, pedidoLuffy, ps5));

        Pedido pedidoSanji = new Pedido();
        pedidoSanji.setCliente(sanjy);
        pedidoSanji.setData(geladeira.getDataCadastro());
        pedidoSanji.adcionarItem(new ItemPedido(2, pedidoSanji, geladeira));
        pedidoSanji.adcionarItem(new ItemPedido(1, pedidoSanji, lavadeira));

        Pedido pedidoNami = new Pedido();
        pedidoNami.setCliente(nami);
        pedidoNami.setData(LocalDate.now());
        pedidoNami.adcionarItem(new ItemPedido(3, pedidoNami, macbook));

        Pedido pedidoZoro = new Pedido();
        pedidoZoro.setCliente(zoro);
        pedidoZoro.setData(LocalDate.now());
        pedidoZoro.adcionarItem(new ItemPedido(1, pedidoZoro, ps5));

        Pedido pedidoUsopp = new Pedido();
        pedidoUsopp.setCliente(usopp);
        pedidoUsopp.setData(dataManipulacao.plusDays(2));
        pedidoUsopp.adcionarItem(new ItemPedido(1, pedidoUsopp, nitendo));
        pedidoUsopp.adcionarItem(new ItemPedido(1, pedidoUsopp, dell));

        entityManager.persist(pedidoLuffy);
        entityManager.persist(pedidoSanji);
        entityManager.persist(pedidoNami);
        entityManager.persist(pedidoZoro);
        entityManager.persist(pedidoUsopp);

        entityManager.getTransaction().commit();

        PedidoDAO pedidoDAO = new PedidoDAO(entityManager);

        BigDecimal valorTotalVendido = pedidoDAO.valorTotalVendido();
        System.out.println("#### TOTAL VENDIDO ####");
        System.out.println("Valor Total R$ " + valorTotalVendido);
        System.out.println("#######################");

        System.out.println("#### RELATÓRIO VENDAS ####");
        List<RelatorioVendasVo> relatorioVendasVos = pedidoDAO.relatorioVendas();
        relatorioVendasVos.forEach(System.out::println);

        Pedido buscarCliente = pedidoDAO.buscarClientePorID(2);

        System.out.println("################################");
        System.out.println(" Cliente.: " + buscarCliente.getCliente().getDadosPessoais().getNome());
        System.out.println("################################");

        ProdutoDAO produtoDAO = new ProdutoDAO(entityManager);
        System.out.println("################################");
        List<Produto> buscarPorParametroNome = produtoDAO.buscarPorParametros("Smart Tv 43", null, null);
        buscarPorParametroNome.forEach(i-> System.out.println("NOME " + i.getNome()));
        System.out.println("################################");

        entityManager.close();
    }

}
