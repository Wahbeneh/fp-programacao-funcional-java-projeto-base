package exercicios;

import exercicios.base.Aula;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Esta é uma classe para você poder implementar as atividades propostas no README.
 * Você <b>NÃO</b> deve alterar:
 * <ul>
 * <li>a estrutura deste arquivo;</li>
 * <li>o nome da classe, dos métodos ou dos atributos;</li>
 * <li>parâmetros e tipo de retorno dos métodos.</li>
 * </ul>
 *
 * <b>Mas você PRECISA alterar valores dos atributos existentes</b>.
 *
 * <p>Você pode alterar o código interno dos métodos, criar métodos auxiliares que podem ser chamados
 * pelos existentes, mas não deve alterar a estrutura dos métodos disponíveis.</p>
 *
 * @author Manoel Campos da Silva Filho
 */
public class Aula06 extends Aula {
    /**
     * {@link Predicate<Estudante>} que seleciona somente as mulheres
     * matriculadas em algum curso e com nota maior ou igual a 6.
     * Este deve ser um predicado composto usando {@link Predicate#and(Predicate)}.
     */
    // CORREÇÃO: Usando expressões lambda para referenciar os métodos de instância corretamente.
    private final Predicate<Estudante> ehMulher = estudante -> estudante.isMulher();
    private final Predicate<Estudante> temCurso = estudante -> estudante.hasCurso();
    private final Predicate<Estudante> estaAprovada = estudante -> estudante.isAprovado();

    private final Predicate<Estudante> mulheresAprovadas =
            ehMulher
                    .and(temCurso)
                    .and(estaAprovada);

    /**
     * Construtor que imprime os resultados dos métodos para verificação.
     */
    public Aula06() {
        System.out.println("--- Mulheres aprovadas ---");
        getEstudantesMulheresAprovadas().forEach(System.out::println);
        System.out.println("\n--- Mulheres aprovadas ordenadas por curso e nota ---");
        getEstudantesMulheresAprovadasOrdenadasPorCursoAndNota().forEach(System.out::println);
        System.out.println("\n--- Mulheres aprovadas ordenadas por curso decrescente e nota crescente ---");
        getEstudantesMulheresAprovadasOrdenadasPorCursoDecrescenteAndNotaCrescente().forEach(System.out::println);
        System.out.println("\n--- Mulheres aprovadas não ordenadas (modificável) ---");
        getEstudantesMulheresAprovadasNaoOrdenadasModificavel().forEach(System.out::println);
        System.out.println("\n--- Mulheres aprovadas ordenadas totalmente decrescente ---");
        getEstudantesMulheresAprovadasOrdenadasTotalmenteDecrescente().forEach(System.out::println);
        System.out.println("\n--- Mulheres aprovadas ordenadas por curso crescente e nota decrescente ---");
        getEstudantesMulheresAprovadasOrdenadasPorCursoCrescenteAndNotaDecrescente().forEach(System.out::println);
    }

    /**
     * Método main para executar a classe.
     */
    public static void main(String[] args) {
        new Aula06();
    }

    /**
     * Obtém uma Lista <b>NÃO-MODIFICÁVEL</b> de mulheres matriculadas e aprovadas em algum curso.
     */
    public List<Estudante> getEstudantesMulheresAprovadas() {
        return estudantes.stream()
                .filter(mulheresAprovadas)
                .toList();
    }

    /**
     * Obtém uma Lista com os mesmos filtros, mas ordenada por curso e nota.
     */
    public List<Estudante> getEstudantesMulheresAprovadasOrdenadasPorCursoAndNota() {
        return estudantes.stream()
                .filter(mulheresAprovadas)
                .sorted(Comparator.comparing(Estudante::getCurso)
                        .thenComparingDouble(Estudante::getNota))
                .toList();
    }

    /**
     * Obtém uma Lista com os mesmos filtros, mas ordenada de forma decrescente pelo curso e crescente pela nota.
     */
    public List<Estudante> getEstudantesMulheresAprovadasOrdenadasPorCursoDecrescenteAndNotaCrescente() {
        return estudantes.stream()
                .filter(mulheresAprovadas)
                .sorted(Comparator.comparing(Estudante::getCurso).reversed()
                        .thenComparingDouble(Estudante::getNota))
                .toList();
    }

    /**
     * Obtém uma Lista <b>MODIFICÁVEL</b> com os mesmos filtros, mas na ordem original.
     */
    public List<Estudante> getEstudantesMulheresAprovadasNaoOrdenadasModificavel() {
        return estudantes.stream()
                .filter(mulheresAprovadas)
                .collect(Collectors.toList());
    }

    /**
     * Obtém uma Lista com os mesmos filtros, mas ordenada de forma decrescente tanto pelo curso quanto pela nota.
     */
    public List<Estudante> getEstudantesMulheresAprovadasOrdenadasTotalmenteDecrescente() {
        return estudantes.stream()
                .filter(mulheresAprovadas)
                .sorted(Comparator.comparing(Estudante::getCurso).reversed()
                        .thenComparing(Estudante::getNota, Comparator.reverseOrder()))
                .toList();
    }

    /**
     * Obtém uma Lista com os mesmos filtros, mas ordenada de forma crescente pelo curso e decrescente pela nota.
     */
    public List<Estudante> getEstudantesMulheresAprovadasOrdenadasPorCursoCrescenteAndNotaDecrescente() {
        return estudantes.stream()
                .filter(mulheresAprovadas)
                .sorted(Comparator.comparing(Estudante::getCurso)
                        .thenComparing(Estudante::getNota, Comparator.reverseOrder()))
                .toList();
    }
}