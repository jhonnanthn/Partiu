package br.com.usjt.web.model;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.caelum.vraptor.ioc.ApplicationScoped;

@ApplicationScoped
public class NaiveBayes {

	private static NaiveBayes singleton;
	private Map<String, int[]> bayes;
	private double entries;
	private Set<String> stopwords;
	private Set<String> modificadores;
	private Set<String> divisores;

	public static synchronized NaiveBayes getInstance() {
		if (singleton == null)
			singleton = new NaiveBayes();
		return singleton;
	}

	public boolean isEmpty() {
		return (!(this.entries > 0));
	}

	public Map<String, int[]> getBayes() {
		return bayes;
	}

	private NaiveBayes() {
		this.bayes = new HashMap<String, int[]>();
		this.entries = 0;

		this.divisores = new HashSet<>(Arrays.asList(new String[] { ".", ",", "(", ")", "!", ";", ":", "?" })); // , "e"

		this.modificadores = new HashSet<>(Arrays.asList(new String[] { "bastante", "bastantes", "grande", "grandes",
				"meio", "muito", "nao", "pequena", "pequenas", "pequeno", "pequenos", "pouca", "poucas", "pouco",
				"poucos", "bem", "boa", "ruim", "agradavel" }));

		// TODO analisar modificadores sozinhos
		this.stopwords = new HashSet<>(Arrays.asList(new String[] { "", " ", "\t", "\n", "a", "a", "achamos", "achei",
				"agora", "ainda", "algo", "alguem", "algum", "alguma", "algumas", "alguns", "ampla", "amplas", "amplo",
				"amplos", "ante", "antes", "ao", "aos", "apos", "aquela", "aquelas", "aquele", "aqueles", "aquilo",
				"as", "as", "ate", "atraves", "cada", "coisa", "coisas", "com", "como", "contra", "contudo", "da",
				"daquele", "daqueles", "das", "de", "dela", "delas", "dele", "deles", "depois", "dessa", "dessas",
				"desse", "desses", "desta", "destas", "deste", "destes", "deve", "devem", "devendo", "dever", "devera",
				"deverao", "deveria", "deveriam", "devia", "deviam", "disse", "disso", "disto", "dito", "diz", "dizem",
				"do", "dos", "e", "e", "ela", "elas", "ele", "eles", "em", "enquanto", "entre", "era", "eram", "eramos",
				"essa", "essas", "esse", "esses", "esta", "esta", "estamos", "estao", "estas", "estava", "estavam",
				"estavamos", "este", "esteja", "estejam", "estejamos", "estes", "esteve", "estive", "estivemos",
				"estiver", "estivera", "estiveram", "estiveramos", "estiverem", "estivermos", "estivesse", "estivessem",
				"estivessemos", "estou", "eu", "fazendo", "fazer", "feita", "feitas", "feito", "feitos", "ficamos",
				"fico", "fiquei", "foi", "fomos", "for", "fora", "foram", "foramos", "forem", "formos", "fosse",
				"fossem", "fossemos", "fui", "ha", "haja", "hajam", "hajamos", "hao", "havemos", // "grande", "grandes",
				"hei", "houve", "houvemos", "houver", "houvera", "houvera", "houveram", "houveramos", "houverao",
				"houverei", "houverem", "houveremos", "houveria", "houveriam", "houveriamos", "houvermos", "houvesse",
				"houvessem", "houvessemos", "isso", "isto", "ja", "la", "la", "lhe", "lhes", "lo", "mais", "mas", "me",
				"mesma", "mesmas", "mesmo", "mesmos", "meu", "meus", "minha", "minhas", "muita", "muitas", // "muito",
				"muitos", "na", "nas", "nem", "nenhum", "nessa", "nessas", "nesta", "nestas", "ninguem", "no", // "nao",
				"nos", "nos", "nossa", "nossas", "nosso", "nossos", "num", "numa", "nunca", "o", "os", "ou", "outra",
				"outras", "outro", "outros", "para", "pela", "pelas", "pelo", "pelos", // "pequena", "pequenas",
																						// "pequeno","pequenos",
				"per", "perante", "pode", "podendo", "poder", "poderia", "poderiam", "podia", "podiam", "pois", "por",
				"porem", "porque", "posso", // "pouca", "poucas", "pouco", "poucos",
				"primeiro", "primeiros", "propria", "proprias", "proprio", "proprios", "pude", "quais", "qual",
				"quando", "quanto", "quantos", "que", "quem", "sao", "se", "seja", "sejam", "sejamos", "sem", "sempre",
				"sendo", "sera", "serao", "serei", "seremos", "seria", "seriam", "seriamos", "seu", "seus", "si",
				"sido", "so", "sob", "sobre", "somos", "sou", "sua", "suas", "talvez", "tambem", "tampouco", "te",
				"tem", "tem", "temos", "tendo", "tenha", "tenham", "tenhamos", "tenho", "ter", "tera", "terao", "terei",
				"teremos", "teria", "teriam", "teriamos", "teu", "teus", "teve", "ti", "tido", "tinha", "tinham",
				"tinhamos", "tive", "tivemos", "tiver", "tivera", "tiveram", "tiveramos", "tiverem", "tivermos",
				"tivesse", "tivessem", "tivessemos", "toda", "todas", "todavia", "todo", "todos", "tu", "tua", "tuas",
				"tudo", "ultima", "ultimas", "ultimo", "ultimos", "um", "uma", "umas", "uns", "vendo", "ver", "vez",
				"vindo", "vir", "voce", "voces", "vos", "vos", "vou", "ta" }));

		/*
		 * sem normalização (tirar acentos) this.stopwords = new
		 * HashSet<>(Arrays.asList(new String[] { "a", "achamos", "achei", "agora",
		 * "ainda", "algo", "algum", "alguma", "algumas", "alguns", "alguém", "ampla",
		 * "amplas", "amplo", "amplos", "ante", "antes", "ao", "aos", "após", "após",
		 * "aquela", "aquelas", "aquele", "aqueles", "aquilo", "as", "através", "até",
		 * "cada", "coisa", "coisas", "com", "como", "contra", "contudo", "da",
		 * "daquele", "daqueles", "das", "de", "dela", "delas", "dele", "deles",
		 * "depois", "dessa", "dessas", "desse", "desses", "desta", "destas", "deste",
		 * "destes", "deve", "devem", "devendo", "dever", "deveria", "deveriam",
		 * "deverá", "deverão", "devia", "deviam", "disse", "disso", "disto", "dito",
		 * "diz", "dizem", "do", "dos", "e", "ela", "elas", "ele", "eles", "em",
		 * "enquanto", "entre", "era", "eram", "essa", "essas", "esse", "esses", "esta",
		 * "estamos", "estas", "estava", "estavam", "este", "esteja", "estejam",
		 * "estejamos", "estes", "esteve", "estive", "estivemos", "estiver", "estivera",
		 * "estiveram", "estiverem", "estivermos", "estivesse", "estivessem",
		 * "estivéramos", "estivéssemos", "estou", "está", "estávamos", "estão", "eu",
		 * "fazendo", "fazer", "feita", "feitas", "feito", "feitos", "ficamos", "fico",
		 * "fiquei", "foi", "fomos", "for", "fora", "foram", "forem", "formos", "fosse",
		 * "fossem", "fui", "fôramos", "fôssemos", "grande", "grandes", "haja", "hajam",
		 * "hajamos", "havemos", "hei", "houve", "houvemos", "houver", "houvera",
		 * "houveram", "houverei", "houverem", "houveremos", "houveria", "houveriam",
		 * "houvermos", "houverá", "houverão", "houveríamos", "houvesse", "houvessem",
		 * "houvéramos", "houvéssemos", "há", "hão", "isso", "isto", "já", "la", "lhe",
		 * "lhes", "lo", "lá", "mais", "mas", "me", "mesma", "mesmas", "mesmo",
		 * "mesmos", "meu", "meus", "minha", "minhas", "muita", "muitas", "muito",
		 * "muitos", "na", "nas", "nem", "nenhum", "nessa", "nessas", "nesta", "nestas",
		 * "ninguém", "no", "nos", "nossa", "nossas", "nosso", "nossos", "num", "numa",
		 * "nunca", "não", "nós", "o", "os", "ou", "outra", "outras", "outro", "outros",
		 * "para", "pela", "pelas", "pelo", "pelos", "pequena", "pequenas", "pequeno",
		 * "pequenos", "per", "perante", "pode", "podendo", "poder", "poderia",
		 * "poderiam", "podia", "podiam", "pois", "por", "porque", "porém", "posso",
		 * "pouca", "poucas", "pouco", "poucos", "primeiro", "primeiros", "própria",
		 * "próprias", "próprio", "próprios", "pude", "quais", "qual", "quando",
		 * "quanto", "quantos", "que", "quem", "se", "seja", "sejam", "sejamos", "sem",
		 * "sempre", "sendo", "serei", "seremos", "seria", "seriam", "será", "serão",
		 * "seríamos", "seu", "seus", "si", "sido", "sob", "sobre", "somos", "sou",
		 * "sua", "suas", "são", "só", "talvez", "também", "tampouco", "te", "tem",
		 * "temos", "tendo", "tenha", "tenham", "tenhamos", "tenho", "ter", "terei",
		 * "teremos", "teria", "teriam", "terá", "terão", "teríamos", "teu", "teus",
		 * "teve", "ti", "tido", "tinha", "tinham", "tive", "tivemos", "tiver",
		 * "tivera", "tiveram", "tiverem", "tivermos", "tivesse", "tivessem",
		 * "tivéramos", "tivéssemos", "toda", "todas", "todavia", "todo", "todos", "tu",
		 * "tua", "tuas", "tudo", "tém", "tínhamos", "um", "uma", "umas", "uns",
		 * "vendo", "ver", "vez", "vindo", "vir", "você", "vocês", "vos", "vou", "vós",
		 * "à", "às", "é", "éramos", "última", "últimas", "último", "últimos", "\t",
		 * "\n", "" }));
		 */
//		this.bayes.put("bom", new int[] { 0, 1, 5, 8 }); // 14 +10 +9 +17 +10 43 60
//		this.bayes.put("horrível", new int[] { 7, 3, 0, 0 });
//		this.bayes.put("ruim", new int[] { 3, 5, 1, 0 });
//		this.bayes.put("ótimo", new int[] { 0, 0, 4, 9 });
//		this.bayes.put("gostei", new int[] { 1, 3, 5, 1 });

//		this.entries = 60;
	}

	private String[] getTokens(String frase) {

		if (frase.isEmpty() || frase == null) {
			return new String[] {};
		}

		System.out.println(frase);

		frase = frase.toLowerCase();

		frase = Normalizer.normalize(frase, Normalizer.Form.NFKD).replaceAll("[^\\p{ASCII}]", "");

		// remove todo char especial
		frase = frase.replaceAll("[@#$%&*-+={}/[/]'`~^_]", " "); // .,()!;:?
		// separa pontuacao
		frase = frase.replace(".", " .");
		frase = frase.replace(",", " ,");
		frase = frase.replace("(", " (");
		frase = frase.replace(")", " )");
		frase = frase.replace("!", " !");
		frase = frase.replace(";", " ;");
		frase = frase.replace(":", " :");
		frase = frase.replace("?", " ?");
//		//remove tabs
		frase = frase.replace("\t", " ");
//		//remove espacos extra (transforma em apenas 1)
		frase = frase.replaceAll(" +", ";");

		// verificação por modificadores
		// une palavra e modificador (nao gostei -> nao-gostei)
		String[] a = frase.split(";");
		for (int i = 0; i < a.length; i++) {
			if (modificadores.contains(a[i])) {
				boolean encontrou = false;

				// procura se há palavra anterior; para caso encontre divisor; pula stopwords
				for (int j = i - 1; j > -1; j--) {

					if (divisores.contains(a[j]))
						break;

					if (!stopwords.contains(a[j])) {
						a[j] += "-" + a[i];
						a[i] = " ";

						encontrou = true;
						break;
					}
				}

				// tenta encontrar palavra posterior caso não tenha encontrado anterior
				// TODO verificar se há problema nesse !divisores
				if (!encontrou) {
					for (int j = i + 1; j < a.length; j++) {
						if (!stopwords.contains(a[j]) && !divisores.contains(a[j])) {
							a[i] += "-" + a[j];
							a[j] = " ";
							break;
						}
					}
				}
			}
		}

		// transforma em set para remoção de duplicatas e stopwords
		Set<String> set = new HashSet<>(Arrays.asList(a));
		set.remove("");
		set.remove(" ");
		set.remove(",");
		set.remove(".");
		set.removeAll(stopwords);

		// print
		for (String x : set) {
			System.out.print(x + ";");
		}
		System.out.println();

		System.out.println("-------------------");
		String[] tokens = new String[] {};
		// retorna tokens como String[]
		return (set.toArray(tokens));

	}

	public void train(String[] frases, Integer[] avaliacao) {

		// converte avaliação de 1 a 5 estrelas para as metrica usadas
		// 0 = mtruim; 1 = ruim; 2 = bom; 3 = mt bom;
		for (int i = 0; i < frases.length; i++) {
			switch (avaliacao[i]) {
			case 1:
				avaliacao[i] = 0;
				break;
			case 2:
				avaliacao[i] = 0;
				break;
			case 3:
				avaliacao[i] = 1;
				break;
			case 4:
				avaliacao[i] = 2;
				break;
			case 5:
				avaliacao[i] = 3;
				break;
			default:
				avaliacao[i] = -1;
				break;
			}
			insert(getTokens(frases[i]), avaliacao[i]);
		}

	};

	private void insert(String[] words, int rating) {
		if (rating != -1) {
			for (String s : words) {
				if (this.bayes.containsKey(s)) {
					int[] temp = this.bayes.get(s);
					temp[rating] += 1;
				} else {
					int[] temp = new int[] { 0, 0, 0, 0 };
					temp[rating]++;
					this.bayes.put(s, temp);
				}
				this.entries++;
			}
		}
	};

	public int getRating(String frase) {

		String[] f = getTokens(frase);
		double[] r = { 0, 0, 0, 0 };
		for (int i = 0; i < f.length; i++) {
			int[] a = bayes.get(f[i]);
			if (a != null) {
				r[0] += a[0] / this.entries;
				r[1] += a[1] / this.entries;
				r[2] += a[2] / this.entries;
				r[3] += a[3] / this.entries;
			}
		}
		System.out.println("Entries: " + this.entries);
		System.out.println("Ratings prediction: 0=" + r[0] + ": 1=" + r[1] + ": 2=" + r[2] + ": 3=" + r[3]);
		int maiorRatingIndex = 0;
		double maiorRating = 0;
		for (int i = 0; i < r.length; i++) {
			if (r[i] > maiorRating) {
				maiorRating = r[i];
				maiorRatingIndex = i;
			}
		}
//		insert(f, maiorRatingIndex);
		return maiorRatingIndex;
	}

	public String showTokenTable() {
		String retorno = "";
		System.out.println("Tokens\t0\t1\t2\t3");
		for (Map.Entry<String, int[]> entry : this.bayes.entrySet()) {
			System.out.print(entry.getKey() + ":\t");
			retorno += entry.getKey() + ":";
			for (int i : entry.getValue()) {
				System.out.print(i + "\t");
				retorno += i + ",";
			}
			System.out.println();
			retorno += ";";
		}
		return retorno;

	}

}
