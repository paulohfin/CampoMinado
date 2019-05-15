package CampoMinado;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author paulohfin
 */
 
public class Campo extends javax.swing.JLabel{

	//Atributos
	private boolean mina;
	private boolean marcador, jogado;
	private int x, y;
	private Tabuleiro tab;
	
	
	/*
	* Construtor da classe
	* recebe 2 inteiros e um objeto do tipo Tabuleiro
	* determina as ações do mouse
	*/
	public Campo(int x, int y, Tabuleiro tab){
		this.x = x;
		this.y = y;
		this.tab = tab;
		this.mina = false;
		this.marcador = false;
		this.jogado = false;
		this.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.setBounds(50 + x * 40, 50 + y * 40, 38,38);
		this.setOpaque(true);
		this.setBackground(Color.gray);
		
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				switch(e.getButton()){
					case 1:
						botaoEsquerdo();
						break;
					case 3:
						botaoDireito();
						break;
				}
			}
		});
	}
	/*
	* métodos get e set
	*/
	public void setMina(){
		this.mina = true;
	}
	public boolean getMina(){
		return this.mina;
	}
	
	/*
	* Método responsável por fazer a ação do click do mouse
	* verifica se há uma mina, se sim, modifica o background do JLabel
	* caso contrário, coloca o número de bombas na vizinhança
	*/
	public void botaoEsquerdo(){
		if(!this.tab.getPerdeu() && !this.jogado && !this.marcador){
		
			this.jogado = true;
			if(this.mina){
				ImageIcon icon = new ImageIcon("CampoMinado/fig/bomba.png");
				Image image = icon.getImage();
				icon = new ImageIcon(image.getScaledInstance(38,38, java.awt.Image.SCALE_SMOOTH));
				this.setIcon(icon);
				this.tab.setPerdeu();
			}
			else{
				int n = this.tab.busca(this.x, this.y);
				if(n > 0)
					this.setText("" + n);
				else{
					if(this.x > 0){
						if(this.y > 0)
							tab.campo[this.x - 1][this.y - 1].botaoEsquerdo();
						if(this.y < tab.getY() - 1)
							tab.campo[this.x - 1][this.y + 1].botaoEsquerdo();
						tab.campo[this.x - 1][this.y].botaoEsquerdo();
					}
					if(this.x < tab.getX() - 1){
						if(this.y > 0)
							tab.campo[this.x + 1][this.y - 1].botaoEsquerdo();
						if(this.y < tab.getY() - 1)
							tab.campo[this.x + 1][this.y + 1].botaoEsquerdo();
						tab.campo[this.x + 1][this.y].botaoEsquerdo();
					}
					if(this.y > 0)
						tab.campo[this.x][this.y - 1].botaoEsquerdo();
					if(this.y < tab.getY() - 1)
						tab.campo[this.x][this.y + 1].botaoEsquerdo();
				}
				this.setBackground(new Color(211,211,211));
			}
		}
	}
	/*
	* Método responsável por colocar ou retirar a bandeira no JLabel
	*/
	public void botaoDireito(){
		if(!this.tab.getPerdeu() && !this.jogado)
			if(!this.marcador){ImageIcon icon = new ImageIcon("CampoMinado/fig/bandeira.png");
				Image image = icon.getImage();
				icon = new ImageIcon(image.getScaledInstance(38,38, java.awt.Image.SCALE_SMOOTH));
				this.setIcon(icon);		
				this.marcador = true;	
			}
			else{
				this.setIcon(null);
				this.marcador = false;
			}
	}
}
