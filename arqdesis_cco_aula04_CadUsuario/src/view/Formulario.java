package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import modelo.Usuario;
import service.UsuarioService;

@SuppressWarnings("serial")
public class Formulario extends JFrame implements ActionListener {

	private JLabel lnome, lcpf, ltipo, lhr, lhrini, lhrfim, ltemp, lcnpj, lsenha, lid, lidcons, funcionario, atendente, sindico,
			sim, nao;
	private JTextField tnome, tcpf, thrini, thrfim, tcnpj, tid, tidcons, tsenha;
	private JPanel ptipo, phora, ptemp, superior, central, inferior;
	private JRadioButton tipo[], temp[];
	private ButtonGroup gptipo, gptemp;
	private MaskFormatter mcpf, mcnpj, mhr, mhrf, msenha;
	private JButton bcad, blimp, bcons, bconst, bexcl, balt;
	private String nome, cpf, hrini, hrfim, cnpj, id, idcons, senha;
	private int perfil;
	private boolean temperatura;
	private UsuarioService service;
	private Usuario usuario;

	public Formulario() {
		
		Container c= getContentPane();
		c.setLayout(new BorderLayout());
		
		service = new UsuarioService();
		
		superior= new JPanel();
		superior.setLayout(new GridLayout(2, 2));
		central= new JPanel();
		central.setLayout(new GridLayout(8, 2));
		inferior= new JPanel();
		inferior.setLayout(new GridLayout(0, 4));
		ptipo = new JPanel();
		ptipo.setLayout(new GridLayout(0, 3));
		phora = new JPanel();
		phora.setLayout(new GridLayout(0, 4));
		ptemp = new JPanel();
		ptemp.setLayout(new GridLayout(0, 2));

		lnome = new JLabel("Nome");
		lcpf = new JLabel("CPF");
		ltipo = new JLabel("Função");
		lhr = new JLabel("Jornada de Trabalho");
		lhrini = new JLabel("Hrs Inicial");
		lhrfim = new JLabel("Hrs Final");
		ltemp = new JLabel("Autorizado a Configurar Temperatura");
		lcnpj = new JLabel("CNPJ da Empresa em que Trabalha");
		lid = new JLabel("ID do Funcionário");
		lidcons= new JLabel("ID para consulta");
		lsenha = new JLabel("Senha");

		funcionario = new JLabel("Funcionario");
		atendente = new JLabel("Atendente");
		sindico = new JLabel("Sindico");

		sim = new JLabel("Sim");
		nao = new JLabel("Não");

		try {
			mcpf = new MaskFormatter("###.###.###-##");
			mcnpj = new MaskFormatter("##.###.###/####-##");
			msenha = new MaskFormatter("AAAA");
			mhr = new MaskFormatter("##:##:##");
			mhrf = new MaskFormatter("##:##:##");
		} catch (Exception e) {
			e.printStackTrace();
		}

		tnome = new JTextField(10);
		tcpf = new JFormattedTextField(mcpf);
		thrini = new JFormattedTextField(mhr);
		thrfim = new JFormattedTextField(mhrf);
		tcnpj = new JFormattedTextField(mcnpj);
		tid = new JTextField(10);
		tid.setEditable(false);
		tid.setText(geraId());
		tidcons= new JTextField(10);
		tsenha = new JFormattedTextField(msenha);

		gptipo = new ButtonGroup();
		String vet[] = { funcionario.getText(), atendente.getText(), sindico.getText() };
		tipo = new JRadioButton[vet.length];
		for (int i = 0; i < vet.length; i++) {
			tipo[i] = new JRadioButton(vet[i]);
			tipo[i].addActionListener(this);
			gptipo.add(tipo[i]);
			ptipo.add(tipo[i]);
		}

		gptemp = new ButtonGroup();
		String vetor[] = { sim.getText(), nao.getText() };
		temp = new JRadioButton[vetor.length];
		for (int i = 0; i < vetor.length; i++) {
			temp[i] = new JRadioButton(vetor[i]);
			temp[i].addActionListener(this);
			gptemp.add(temp[i]);
			ptemp.add(temp[i]);
		}

		bcad = new JButton("Cadastrar");
		bcad.addActionListener(this);
		bcad.setEnabled(true);
		blimp = new JButton("Limpar");
		blimp.addActionListener(this);		
		bcons = new JButton("Consultar");
		bcons.addActionListener(this);
		bcons.setEnabled(true);
		bconst = new JButton("Cons. Todos");
		bconst.addActionListener(this);
		bexcl = new JButton("Excluir");
		bexcl.addActionListener(this);
		bexcl.setEnabled(false);
		balt = new JButton("Alterar");
		balt.addActionListener(this);
		balt.setEnabled(false);
		
		//definir quais botoes serão sensiveis e seu layout

		phora.add(lhrini);
		phora.add(thrini);
		phora.add(lhrfim);
		phora.add(thrfim);

		superior.add(lidcons);
		superior.add(tidcons);
		superior.add(bcons);
		superior.add(bconst);		
		
		central.add(lid);
		central.add(tid);
		central.add(lsenha);
		central.add(tsenha);
		central.add(lnome);
		central.add(tnome);
		central.add(lcpf);
		central.add(tcpf);
		central.add(ltipo);
		central.add(ptipo);
		central.add(ltemp);
		central.add(ptemp);
		central.add(lhr);
		central.add(phora);
		central.add(lcnpj);
		central.add(tcnpj);
		
		inferior.add(bcad);
		inferior.add(balt);
		inferior.add(bexcl);
		inferior.add(blimp);
		
		c.add(superior, BorderLayout.NORTH);
		c.add(central, BorderLayout.CENTER);
		c.add(inferior, BorderLayout.SOUTH);

		setSize(700, 310);
		setLocationRelativeTo(null);
		setVisible(true);

	}

	public String geraId() {
		double randomico = Math.random() * 10000;
		int id = (int) randomico;
		return "" + id;
	}

	public void captura() {
		nome = tnome.getText();
		cpf = tcpf.getText();
		hrini = thrini.getText();
		hrfim = thrfim.getText();
		cnpj = tcnpj.getText();
		id = tid.getText();
		idcons= tidcons.getText();
		senha = tsenha.getText();
	}

	public void limpa() {
		tnome.setText("");
		tcpf.setText("");
		thrini.setText("");
		thrfim.setText("");
		tcnpj.setText("");
		tsenha.setText("");
		tidcons.setText("");

		gptipo.clearSelection();
		gptemp.clearSelection();
	}

	public void cadastrar() {

		boolean sucesso = false;
		captura();

		if (nome.equals("") || cpf.equals("   .   .   -  ") || id.equals("") || senha.equals("    ")
				|| hrini.equals("  :  :  ") || hrfim.equals("  :  :  ") || perfil == -1
				|| cnpj.equals("  .   .   /    -  ")) {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Atenção!", 2);
		} else {
			usuario = new Usuario(id, nome, senha, cpf, hrini, hrfim, temperatura, perfil, cnpj);
			sucesso = service.cadastrar(usuario);

			if (sucesso){
				JOptionPane.showMessageDialog(null, "Dados cadastrados com sucesso", "Mensagem", 1);
				limpa();
				tid.setText(geraId());
			}else{
				JOptionPane.showMessageDialog(null, "Falha ao cadastrar", "Atenção!", 2);
			}	
		}

	}

	public void consultar() {

		usuario = new Usuario();
		captura();

		if (idcons.equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo ID!", "Atenção!", 2);
		} else {
			usuario = service.consultar(idcons);

			tnome.setText(usuario.getNome());
			tcpf.setText(usuario.getCpf());
			thrini.setText(usuario.getHoraEnt());
			thrfim.setText(usuario.getHoraSai());
			tcnpj.setText(usuario.getCnpj());
			tid.setText(usuario.getId());
			tsenha.setText(usuario.getSenha());

			perfil = usuario.getPerfil();
			tipo[perfil].setSelected(true);

			temperatura = usuario.isMudaTemp();
			
			bcad.setEnabled(false);
			bexcl.setEnabled(true);
			balt.setEnabled(true);
			
			if (temperatura)
				temp[0].setSelected(true);
			else
				temp[1].setSelected(true);
		}
	}

	public void consultarTodos() {

		ArrayList<Usuario> array = new ArrayList<Usuario>();
		array = service.consultarTodos();
		String x = "";

		for (Usuario u : array) {
			x += "ID: " + u.getId() + 
				 "\nNome: " + u.getNome() + 
				 "\nCPF: " + u.getCpf() + 
				 "\nCNPJ Empresa: " + u.getCnpj()+ "\n\n";
		}
		JOptionPane.showMessageDialog(null, x);
	}

	public void alterar() {

		boolean sucesso = false;
		captura();

		if (nome.equals("") || cpf.equals("   .   .   -  ") || id.equals("") || senha.equals("    ")
				|| hrini.equals("  :  :  ") || hrfim.equals("  :  :  ") || perfil == -1
				|| cnpj.equals("  .   .   /    -  ")) {
			JOptionPane.showMessageDialog(null, "Preencha todos os campos!", "Atenção!", 2);
		} else {
			usuario = new Usuario(id, nome, senha, cpf, hrini, hrfim, temperatura, perfil, cnpj);
			sucesso = service.alterar(usuario);

			if (sucesso) {
				JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso", "Mensagem", 1);
				limpa();
				bcad.setEnabled(true);
				bexcl.setEnabled(false);
				balt.setEnabled(false);
				tid.setText(geraId());
			} else {
				JOptionPane.showMessageDialog(null, "Falha ao atualizar", "Atenção!", 2);
			}
		}
	}

	public void excluir() {

		boolean sucesso = false;
		captura();

		if (id.equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo ID!", "Atenção!", 2);
		} else {
			sucesso = service.excluir(id);

			if (sucesso) {
				JOptionPane.showMessageDialog(null, "Usuario excluido com sucesso", "Mensagem", 1);
				limpa();
				bcad.setEnabled(true);
				bexcl.setEnabled(false);
				balt.setEnabled(false);
				tid.setText(geraId());
			} else {
				JOptionPane.showMessageDialog(null, "Falha ao excluir", "Atenção!", 2);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		for (int i = 0; i < tipo.length; i++) {
			if (tipo[i].isSelected()) {
				perfil = i;
			}
		}

		for (int i = 0; i < temp.length; i++) {
			if (temp[i].isSelected()) {
				if (i == 0)
					temperatura = true;
				else
					temperatura = false;
			}
		}

		if (e.getSource() == bcad) {
			cadastrar();
		}
		
		if(e.getSource() == bcons){
			consultar();	
		}
		
		if(e.getSource() == bconst){
			consultarTodos();
		}
		
		if(e.getSource() == balt){
			alterar();			
		}
		
		if(e.getSource() == bexcl){
			excluir();
		}

		if (e.getSource() == blimp) {
			limpa();
			bcad.setEnabled(true);
			bexcl.setEnabled(false);
			balt.setEnabled(false);
			tid.setText(geraId());
		}

	}

}
