package CampoMinado;


import java.awt.Color;
import javax.swing.*;


/**
 *
 * @author paulohfin
 */
 
public class Tabuleiro extends javax.swing.JFrame{
	
	//Atributos da classe
	Campo [][] campo;
	private int x, y;
	private boolean perdeu;
	
	/*
	* Construtor da classe
	* recebe 3 inteiros, largura, altura e quantidade de bombas
	* instancia x * y objetos do tipo Campo
	* seleciona aleatoriamente a quantidade de bombas
	* determina o tamanho do JFrame
	*/
	public Tabuleiro(int x, int y, int bomba){
		campo = new Campo[x][y];
		this.x = x;
		this.y = y;
		this.perdeu = false;
		
		for(int i = 0; i < x; i++)
			for(int j = 0; j < y; j++){
				Campo aux = new Campo(i,j, this);
				
				campo[i][j] = aux;
				this.add(aux);
			}
		
		while(bomba > 0){
			int l, c;
			l = (int)(Math.random() * x);
			c = (int)(Math.random() * y);
			if(!campo[l][c].getMina()){
				campo[l][c].setMina();
				bomba--;
			}
		}
		this.setLayout(null);
		this.setSize(100 + this.x * 40,150 + this.y * 40);
		this.setVisible(true);
	}
	/*
	* Método responsável por buscar a quantidade de mina em uma determinada coordenada
	* recebe dois inteiros que são as coordenadas (x, y)
	* conta quantas minas há em sua vizinhança
	* retorna um inteiro com a quantidade de mina na vizinhança
	*/
	public int busca(int x, int y){
		int n = 0;
		if(x > 0){
			if(y > 0)
				if(campo[x - 1][y - 1].getMina())
					n++;
			if(y < this.y - 1)
				if(campo[x - 1][y + 1].getMina())
					n++;
			if(campo[x - 1][y].getMina())
				n++;
		}
		if(x < this.x - 1){
			if(y > 0)
				if(campo[x + 1][y - 1].getMina())
					n++;
			if(y < this.y - 1)
				if(campo[x + 1][y + 1].getMina())
					n++;
			if(campo[x + 1][y].getMina())
				n++;
		}		
		if(y > 0)
			if(campo[x][y - 1].getMina())
				n++;
		if(y < this.y - 1)
			if(campo[x][y + 1].getMina())
				n++;
		return n;
	}
	/*
	* metodos get e set
	*/
	public boolean getPerdeu(){
		return this.perdeu;
	}
	public void setPerdeu(){
		this.perdeu = true;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
}
