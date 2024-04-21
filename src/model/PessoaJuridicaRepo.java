package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PessoaJuridicaRepo {
    private List<PessoaJuridica> pessoasJuridicas;

    public PessoaJuridicaRepo() {
        pessoasJuridicas = new ArrayList<>();
    }

    public void inserir(PessoaJuridica pessoaJuridica) {
    pessoasJuridicas.add(pessoaJuridica);
}

    public void alterar(PessoaJuridica pessoaJuridica) {
        for (int i = 0; i < pessoasJuridicas.size(); i++) {
            if (pessoasJuridicas.get(i).getId() == pessoaJuridica.getId()) {
                pessoasJuridicas.set(i, pessoaJuridica);
                return;
            }
        }
        throw new IllegalArgumentException("Pessoa com o ID especificado não encontrada para alteração");
    }

    public void excluir(int id) {
    for (int i = 0; i < pessoasJuridicas.size(); i++) {
        if (pessoasJuridicas.get(i).getId() == id) {
            pessoasJuridicas.remove(i);
            return;
        }
    }
    throw new IllegalArgumentException("Pessoa com o ID especificado não encontrada para exclusão");
}

    public PessoaJuridica obter(int id) {
    for (PessoaJuridica pessoa : pessoasJuridicas) {
        if (pessoa.getId() == id) {
            return pessoa;
        }
    }
    throw new IllegalArgumentException("Pessoa com o ID especificado não encontrada");
}

    public List<PessoaJuridica> obterTodos() {
        return new ArrayList<>(pessoasJuridicas);
    }

    public void persistir(String nomeArquivo) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(nomeArquivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(pessoasJuridicas);
        }
    }

    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(nomeArquivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            pessoasJuridicas = (List<PessoaJuridica>) ois.readObject();
        }
    }
}