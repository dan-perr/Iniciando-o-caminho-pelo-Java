package projetopoo;

import java.util.List;
import java.io.IOException;
import java.util.Scanner;
import model.PessoaFisicaRepo;
import model.PessoaFisica;
import model.PessoaJuridicaRepo;
import model.PessoaJuridica;

public class ProjetoPOO {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            PessoaFisicaRepo repo1 = new PessoaFisicaRepo();
            PessoaJuridicaRepo repo2 = new PessoaJuridicaRepo();
            boolean continuar = true;
            
            while (continuar) {
                System.out.println("===================");
                System.out.println("1 - Incluir Pessoa");
                System.out.println("2 - Alterar Pessoa");
                System.out.println("3 - Excluir Pessoa");
                System.out.println("4 - Buscar pelo ID");
                System.out.println("5 - Exibir Todos");
                System.out.println("6 - Persistir Dados");
                System.out.println("7 - Recuperar Dados");
                System.out.println("0 - Finalizar Programa");
                System.out.println("===================");
                int opcao = scanner.nextInt();
                
                switch (opcao) {
                    case 1:
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                        char tipoPessoaChar = scanner.next().toUpperCase().charAt(0);
                        scanner.nextLine();
                        
                        if (tipoPessoaChar == 'F') {
                            System.out.println("Informe o ID: ");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            
                            System.out.println("Insira os dados...");
                            
                            System.out.println("Informe o nome: ");
                            String nome = scanner.nextLine();
                            
                            System.out.println("Informe o CPF: ");
                            String cpf = scanner.next();
                            
                            System.out.println("Informe a idade: ");
                            int idade = scanner.nextInt();
                            
                            repo1.inserir(new PessoaFisica(id, nome, cpf, idade));
                        } else if (tipoPessoaChar == 'J') {
                            System.out.println("Informe o ID, nome e CNPJ da Pessoa Juridica:");
                            int id = scanner.nextInt();
                            scanner.nextLine();
                            
                            System.out.println("Informe o nome:");
                            String nome = scanner.nextLine();
                            
                            System.out.println("Informe o CNPJ:");
                            String cnpj = scanner.next();
                            
                            repo2.inserir(new PessoaJuridica(id, nome, cnpj));
                        } else {
                            System.out.println("Opcao inválida. Tente novamente.");
                        }
                        break;
                    case 2:
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                        char tipoAlteracaoChar = scanner.next().toUpperCase().charAt(0);
                        scanner.nextLine();
                        
                        System.out.println("Informe o ID da pessoa:");
                        int idAlteracao = scanner.nextInt();
                        scanner.nextLine();
                        
                        System.out.println("Insira os dados...");
                        
                        if (tipoAlteracaoChar == 'F') {
                            PessoaFisica pessoaFisicaExistente = repo1.obter(idAlteracao);
                            if (pessoaFisicaExistente != null) {
                                System.out.println("Informe o novo nome:");
                                String novoNome = scanner.nextLine();
                                
                                System.out.println("Informe o novo CPF:");
                                String novoCpf = scanner.nextLine();
                                
                                System.out.println("Informe a nova idade:");
                                int novaIdade = Integer.parseInt(scanner.nextLine());
                                
                                PessoaFisica pessoaFisicaAlterada = new PessoaFisica(idAlteracao, novoNome, novoCpf, novaIdade);
                                
                                repo1.alterar(idAlteracao, pessoaFisicaAlterada);
                                System.out.println("Pessoa Fisica alterada com sucesso");
                            } else {
                                System.out.println("Pessoa Fisica nao encontrada.");
                            }
                        } else if (tipoAlteracaoChar == 'J') {
                            PessoaJuridica pessoaJuridica = repo2.obter(idAlteracao);
                            if (pessoaJuridica != null) {
                                System.out.println("Dados atuais:");
                                pessoaJuridica.exibir();
                                System.out.println("Informe o novo nome: ");
                                System.out.println("Informe o novo CNPJ: ");
                                scanner.nextLine();
                                String nomeAlteracao = scanner.nextLine();
                                String cnpjAlteracao = scanner.next();
                                pessoaJuridica.setNome(nomeAlteracao);
                                pessoaJuridica.setCnpj(cnpjAlteracao);
                                repo2.alterar(pessoaJuridica);
                                System.out.println("Pessoa Juridica alterada com sucesso.");
                            } else {
                                System.out.println("Pessoa Juridica nao encontrada.");
                            }
                        } else {
                            System.out.println("Opcao inválida. Tente novamente.");
                        }
                        break;
                    case 3:
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                        char tipoExclusaoChar = scanner.next().toUpperCase().charAt(0);
                        scanner.nextLine();

                        System.out.println("Informe o ID da pessoa:");
                        int idExclusao = scanner.nextInt();
                        
                        System.out.println("Insira os dados...");

                        if (tipoExclusaoChar == 'F') {
                            PessoaFisica pessoaFisica = repo1.obter(idExclusao);
                            if (pessoaFisica != null) {
                                repo1.excluir(idExclusao);
                                System.out.println("Pessoa Fisica excluida com sucesso.");
                            } else {
                                System.out.println("Pessoa Fisica nao encontrada.");
                            }
                        } else if (tipoExclusaoChar == 'J') {
                            PessoaJuridica pessoaJuridica = repo2.obter(idExclusao);
                            if (pessoaJuridica != null) {
                                repo2.excluir(idExclusao);
                                System.out.println("Pessoa Juridica excluida com sucesso.");
                            } else {
                                System.out.println("Pessoa Juridica nao encontrada.");
                            }
                        } else {
                            System.out.println("Opcao inválida. Tente novamente.");
                        }
                        break;
                    case 4:
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                        char tipoObtencaoChar = scanner.next().toUpperCase().charAt(0);
                        scanner.nextLine();
                        
                        System.out.println("Informe o ID da pessoa:");
                        int idObtencao = scanner.nextInt();
                        
                        System.out.println("Insira os dados...");
                        
                    switch (tipoObtencaoChar) {
                        case 1 -> {
                            PessoaFisica pessoaFisica = repo1.obter(idObtencao);
                            if (pessoaFisica != null) {
                                System.out.println("Dados da Pessoa Fisica:");
                                pessoaFisica.exibir();
                            } else {
                                System.out.println("Pessoa Fisica nao encontrada.");
                            }
                        }
                        case 2 -> {
                            PessoaJuridica pessoaJuridica = repo2.obter(idObtencao);
                            if (pessoaJuridica != null) {
                                System.out.println("Dados da Pessoa Juridica:");
                                pessoaJuridica.exibir();
                            } else {
                                System.out.println("Pessoa Juridica nao encontrada.");
                            }
                        }
                        default -> System.out.println("Opcao inválida. Tente novamente.");
                    }
                        break;


                    case 5:
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                        char tipoListagemChar = scanner.next().toUpperCase().charAt(0);
                        
                    switch (tipoListagemChar) {
                        case 1 -> {
                            List<PessoaFisica> pessoasFisicas = repo1.obterTodos();
                            System.out.println("Pessoas Fisicas:");
                            for (PessoaFisica pessoa : pessoasFisicas) {
                                pessoa.exibir();
                            }
                        }
                        case 2 -> {
                            List<PessoaJuridica> pessoasJuridicas = repo2.obterTodos();
                            System.out.println("Pessoas Juridicas:");
                            for (PessoaJuridica pessoa : pessoasJuridicas) {
                                pessoa.exibir();
                            }
                        }
                        default -> System.out.println("Opcao inválida. Tente novamente.");
                    }
                        break;
                        
                    case 6:
                        System.out.println("Informe o prefixo dos arquivos:");
                        String prefixo = scanner.next();

                        repo1.persistir(prefixo + ".fisica.bin");
                        repo1.persistir("C:/ProjetoNetBeans/ProjetoPOO/src/model/pessoas_fisicas.bin");
                        System.out.println("Dados de Pessoa Fisica salvos em " + prefixo + ".fisica.bin.");

                        try {
                            repo2.persistir(prefixo + ".juridica.bin");
                            repo2.persistir("C:/ProjetoNetBeans/ProjetoPOO/src/model/pessoas_juridicas.bin");
                            System.out.println("Dados de Pessoa Juridica salvos em " + prefixo + ".juridica.bin.");
                        } catch (IOException e) {
                            System.out.println("Erro ao salvar os dados das pessoas juridicas: " + e.getMessage());
                        }
                    case 7:
                        System.out.println("Informe o prefixo dos arquivos:");
                        String prefixoRecuperacao = scanner.next();
                        
                        repo1.recuperar(prefixoRecuperacao + ".fisica.bin");
                        System.out.println("Dados de Pessoa Fisica recuperados de " + prefixoRecuperacao + ".fisica.bin.");
                        
                        try {
                            repo2.recuperar(prefixoRecuperacao + ".juridica.bin");
                            System.out.println("Dados de Pessoa Juridica recuperados de " + prefixoRecuperacao + ".juridica.bin.");
                        } catch (IOException e) {
                            System.out.println("Erro ao recuperar os dados das pessoas juridicas: " + e.getMessage());
                        } catch (ClassNotFoundException e) {
                            System.out.println("Erro ao recuperar os dados das pessoas juridicas: " + e.getMessage());
                        }
                        break;

                    case 0:
                        System.out.println("Saindo do sistema.");
                        continuar = false;
                        break;
                    default:
                        System.out.println("Opcao invalida. Tente novamente.");
                }
            }
        }
    }
}