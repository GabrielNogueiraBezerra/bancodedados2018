package Teste;

import Models.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel
 */
public class Teste {

    public static void main(String[] args) {
        try {

            //Aluno aluno = new Aluno();
            //aluno.setContato("88997035120");
            //aluno.setCurso("Engenharia de Software");
            //aluno.setEmail("gabrielnogueirabezerra@gmail.com");
            //aluno.setNome("Gabriel Nogueira Bezerra");
            //aluno.setSituacao(true);
            //aluno.buscar(2);
            //aluno.excluir();
            //aluno.setEmail("outroemail@gmail.com");
            //aluno.alterar();
            //System.out.println(aluno.getEmail());
            
            
            //Funcionario funcionario = new Funcionario();
            //funcionario.setContato("88997035120");
            //funcionario.setLogin("gabrielnogueirabezerra@gmail.com");
            //funcionario.setNome("Gabriel Nogueira Bezerra");
            //funcionario.setSenha("123456");
            //funcionario.inserir();
            
            //funcionario.buscar(2);
            //funcionario.excluir();
            //funcionario.setNome("outro nome");
            //funcionario.alterar();
            //System.out.println(funcionario.getNome());
            
            //Livro livro = new Livro();
            //livro.buscar(2);
            //livro.setEdicao("Edicao 2");
            //livro.alterar();
            
            //System.out.println(livro.getEdicao());
            
            //livro.excluir();
            //livro.setAutor("Autor 1");
            //livro.setCategoria("Categoria 1");
            //livro.setEdicao("Edicao 1");
            //livro.setResenha("Resenha 1");
            //livro.setTitulo("Titulo 1");
            
            //livro.inserir();
            
            //Exemplar exemplar = new Exemplar();
            //exemplar.setSituacao(true);
            //Livro livro = new Livro();
            //livro.buscar(1);
            //exemplar.setLivro(livro);
            //exemplar.buscar(2);
            //exemplar.setSituacao(false);
            //exemplar.alterar();
            //exemplar.excluir();
            
            Emprestimo emprestimo = new Emprestimo();
            //Aluno aluno = new Aluno();
            //aluno.buscar(1);
            //emprestimo.setAluno(aluno);
            
            //emprestimo.setDataEmprestimo(new Date(1000));
            //emprestimo.setDataPrevista(new Date(10));
            //Exemplar exemplar = new Exemplar();
            //exemplar.buscar(1);
            //emprestimo.setExemplar(exemplar);
            //Funcionario funcionario = new Funcionario();
            //funcionario.buscar(1);
            //emprestimo.setFuncionario(funcionario);
            
            //emprestimo.setRenovacoes(0);
            
            //emprestimo.inserir();
            
            emprestimo.buscar(1);
            //emprestimo.setRenovacoes(1);
            //emprestimo.alterar();
            
           Devolucao devolucao = new Devolucao();
           
           //devolucao.setData(new Date(100));
           //devolucao.setEmprestimo(emprestimo);
           
           //Funcionario funcionario = new Funcionario();
           //funcionario.buscar(1);
           
           //devolucao.setFuncionario(funcionario);
           //devolucao.setMulta(0);
           
           //devolucao.inserir();
           
           devolucao.buscar(2);
           
           //devolucao.setMulta(10);
           
           //devolucao.alterar();
           
           devolucao.excluir();
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Teste.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
