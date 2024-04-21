package model;

import java.util.List;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

public class PessoaFisicaRepo{
    private List<PessoaFisica> pessoasFisicas;

    public PessoaFisicaRepo() {
        pessoasFisicas = new ArrayList<>();
    }

    public void inserir(PessoaFisica pessoaFisica) {
        pessoasFisicas.add(pessoaFisica);
    }

    public void alterar(int indice, PessoaFisica pessoaFisica) {
        if (indice >= 0 && indice < pessoasFisicas.size()) {
            pessoasFisicas.set(indice, pessoaFisica);
        } else {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
    }

    public void excluir(int indice) {
        if (indice >=0 && indice < pessoasFisicas.size()) {
            pessoasFisicas.remove(indice);
        } else {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
    }
    
    public PessoaFisica obter(int indice) {
        if (indice >= 0 && indice < pessoasFisicas.size()) {
            return pessoasFisicas.get(indice);
        } else {
            throw new IndexOutOfBoundsException("Índice inválido");
        }
    }
    
    public List<PessoaFisica> obterTodos() {
        return new ArrayList<>(pessoasFisicas);
    }
    
    public void persistir(String nomeArquivo) {
        try (FileOutputStream fos = new FileOutputStream(nomeArquivo);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(pessoasFisicas);
        } catch (IOException e) {
            System.err.println("Erro ao persistir dados: " + e.getMessage());
        }
    }

    public void recuperar(String nomeArquivo) {
        try (FileInputStream fis = new FileInputStream(nomeArquivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            pessoasFisicas = (List<PessoaFisica>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao recuperar dados: " + e.getMessage());
        }
    }
}