//Grupo: Fabiane, Rafael Alves, Savanna e Franciele
// A implementação utiliza divisão e conquista para preencher a tabela. Inicialmente a tabela é dividida em
//.. quatro quadrantes, então é visto em qual quadrante o buraco se encontra. O quadrante com o buraco é o
//.. primeiro a ser preenchido. Após o preenchimento do quadrante com o buraco, é inserida uma peça no meio
//.. da tabela, servindo como "buraco" para os outros quadrantes, que então são preenchidos recursivamente.

import java.util.*;

public class Trimino{
	
	private int[][] tabela;
	private int numAtual;
	
	public Trimino(int tam, int x, int y) {

		int tamAtual = 1;
		while (tamAtual < tam) tamAtual*=2;

// A tabela deve ser potência de 2
		tabela = new int[tamAtual][tamAtual];
		numAtual = 1;
		
// Preenche toda a tabela com espaços em branco
		for (int i=0; i<tamAtual; i++) {
			for (int j=0; j<tamAtual; j++) {
				tabela[i][j] = 0;
			}
		}

// Adiciona o buraco da tabela
		tabela[x][y] = -1;
	}
	
	public int getNumAtual(){
		return this.numAtual;
	}
// Método público para a chamada recursiva
	public void jogo() {
		jogoRec(tabela.length, 0, 0);
	}
	
	private void jogoRec(int tam, int topx, int topy) {
		
// Caso base, quando a tabela é 2x2 e é necessário apenas colocar uma peça 
		if (tam == 2) {
		
// Preenche a tabela com a peça necessária, de acordo com a posição do buraco
			for (int i=0; i<tam; i++) 
				for (int j=0; j<tam; j++)
					if (tabela[topx+i][topy+j] == 0)
						tabela[topx+i][topy+j] = numAtual;
		
// Pula para a próxima peça
			numAtual++;
		}
		
// Chamada recursiva fora do caso base
		else {
			
// Salva as coordenadas recebidas na recursão
			int salvaX=topx, salvaY=topy;
			
			for (int x=topx; x<topx+tam; x++) 
				for (int y=topy; y<topy+tam; y++)
					if (tabela[x][y] != 0) {
						salvaX = x;
						salvaY = y;
					}
				
// Verifica se o buraco está no quadrante superior esquerdo
			if (salvaX < topx + tam/2 && salvaY < topy + tam/2) {
				
// Chama o método recursivo para o quadrante superior esquerdo
				jogoRec(tam/2, topx, topy);
				
// Coloca um peça no meio
				tabela[topx+tam/2][topy+tam/2-1] = numAtual;
				tabela[topx+tam/2][topy+tam/2] = numAtual;
				tabela[topx+tam/2-1][topy+tam/2] = numAtual;
				
// Pula para a próxima peça
				numAtual++;
				
// Faz as próximas chamadas recursivas dos outros quadrantes
				jogoRec(tam/2, topx, topy+tam/2);
				jogoRec(tam/2, topx+tam/2, topy);
				jogoRec(tam/2, topx+tam/2, topy+tam/2);
				
			}
			
// Se o buraco estiver no quadrante superior direito
			else if (salvaX < topx + tam/2 && salvaY >= topy + tam/2) {
				
// Chama o método recursivo para o quadrante superior direito
				jogoRec(tam/2, topx, topy+tam/2);
				
// Coloca uma peça no meio
				tabela[topx+tam/2][topy+tam/2-1] = numAtual;
				tabela[topx+tam/2][topy+tam/2] = numAtual;
				tabela[topx+tam/2-1][topy+tam/2-1] = numAtual;
				
// Pula para a próxima peça
				numAtual++;
				
// Faz as próximas chamadas recursivas dos outros quadrantes
				jogoRec(tam/2, topx, topy);
				jogoRec(tam/2, topx+tam/2, topy);
				jogoRec(tam/2, topx+tam/2, topy+tam/2);
				
			}
			
// Se o buraco estiver no quadrante inferior esquerdo
			else if (salvaX >= topx + tam/2 && salvaY < topy + tam/2) {
				
// Chama o método recursivo para o quadrante inferior esquerdo
				jogoRec(tam/2, topx+tam/2, topy);
				
// Coloca uma peça no meio
				tabela[topx+tam/2-1][topy+tam/2] = numAtual;
				tabela[topx+tam/2][topy+tam/2] = numAtual;
				tabela[topx+tam/2-1][topy+tam/2-1] = numAtual;
				
// Pula para a próxima peça
				numAtual++;
				
// Faz as próximas chamadas recursivas dos outros quadrantes
				jogoRec(tam/2, topx, topy);
				jogoRec(tam/2, topx, topy+tam/2);
				jogoRec(tam/2, topx+tam/2, topy+tam/2);
			}
// Se o buraco estiver no quadrante inferior direito			
			else {
				
// Chama o método recursivo para o quadrante inferior direito
				jogoRec(tam/2, topx+tam/2, topy+tam/2);
				
// Coloca uma peça no meio
				tabela[topx+tam/2-1][topy+tam/2] = numAtual;
				tabela[topx+tam/2][topy+tam/2-1] = numAtual;
				tabela[topx+tam/2-1][topy+tam/2-1] = numAtual;
				
// Pula para a próxima peça
				numAtual++;
				
// Faz as próximas chamadas recursivas dos outros quadrantes
				jogoRec(tam/2, topx+tam/2, topy);
				jogoRec(tam/2, topx, topy+tam/2);
				jogoRec(tam/2, topx, topy);
			}
			
		} 
	} 
	
	
// Apresenta na tela o jogo
	public void print() {
		
		for (int i=0; i<tabela.length; i++) {
			for (int j=0; j<tabela[i].length; j++)
				System.out.print(tabela[i][j] + "\t");
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
// Leitura do teclado		
		Scanner stdin = new Scanner(System.in);
		System.out.println("Qual o tamanho da tabela? Deve ser potência de 2");
		int tam = stdin.nextInt();
		
		System.out.println("Onde está o buraco? (coordenadas x e y, separadas por espaço)");
		int x = stdin.nextInt();
		int y = stdin.nextInt();
		
// Cria o objeto triminó com os dados inseridos
		Trimino objeto = new Trimino(tam, x, y);
		objeto.jogo();
		
// Imprime a tabela final, com as peças colocadas
		System.out.println("Tabela final com os triminós:\n");
		objeto.print();
		System.out.printf("Quantidade de peças utilizadas: "+(objeto.getNumAtual()-1));
		
	}
}