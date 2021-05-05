package com.eziom.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.eziom.cursomc.domain.Categoria;
import com.eziom.cursomc.domain.Cidade;
import com.eziom.cursomc.domain.Cliente;
import com.eziom.cursomc.domain.Endereco;
import com.eziom.cursomc.domain.Estado;
import com.eziom.cursomc.domain.Produto;
import com.eziom.cursomc.domain.enums.TipoCliente;
import com.eziom.cursomc.repositories.CategoriaRepository;
import com.eziom.cursomc.repositories.CidadeRepository;
import com.eziom.cursomc.repositories.ClienteRepository;
import com.eziom.cursomc.repositories.EnderecoRepository;
import com.eziom.cursomc.repositories.EstadoRepository;
import com.eziom.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Infomática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador",2000.00);
		Produto p2 = new Produto(null, "Impressora",800.00);
		Produto p3 = new Produto(null, "Mouse",80.00);
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlândia", est1);
		Cidade c2 = new Cidade(null,"São Paulo", est2);
		Cidade c3 = new Cidade(null,"Campinas", est2);
		
		Cliente cli1 = new Cliente(null, "Maria Silva","maria@gmail.com","1111111",TipoCliente.PESSOAFISICA);
		
		Endereco e1 = new Endereco(null, "rua Flores", "300", "Apto 303", "Jardim", "1231413", cli1, c1);
		Endereco e2 = new Endereco(null, "rua matos", "105", "Apto 03", "São José", "4234213", cli1, c2);
		
		cli1.getTelefones().addAll(Arrays.asList("12345678","87654321"));
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
	}

}
