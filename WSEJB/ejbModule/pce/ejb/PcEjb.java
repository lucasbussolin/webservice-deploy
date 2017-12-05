package pce.ejb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.ejb.Stateless;

import pc.dao.CpuDao;
import pc.dao.GpuDao;
import pc.dao.MoboDao;
import pc.dao.PsuDao;
import pc.dao.RamDao;
import viewobject.personalcomputer.Cpu;
import viewobject.personalcomputer.Gpu;
import viewobject.personalcomputer.PlacaMae;
import viewobject.personalcomputer.Psu;
import viewobject.personalcomputer.Ram;

@Stateless
public class PcEjb {

	private final GpuDao gpudao = new GpuDao();
	private final MoboDao mobodao = new MoboDao();
	private final RamDao ramdao = new RamDao();
	private final PsuDao psudao = new PsuDao();

	public Double pesquisaMediaPreco(String pesquisa) {
		Double retorno = null;
		try {
			pesquisa = pesquisa.replace(" ", "+");
			pesquisa = pesquisa.trim();
			String inicio = "https://www.kabum.com.br/cgi-local/site/listagem/listagem.cgi?string=", fim = "&btnG=";
			Integer divisor = 0, i, j;
			System.out.println("Pesquisando no Kabum!");
			URL kabum = new URL(inicio + pesquisa + fim);
			BufferedReader in = new BufferedReader(new InputStreamReader(kabum.openStream(), "utf-8"));

			Double media = 0.00;
			String inputLine, tag = "<div class=\"listagem-preco\">", preco;

			while ((inputLine = in.readLine()) != null) {
				if (inputLine.contains(tag)) {
					@SuppressWarnings("unused")
					Character divTag;
					i = 0;
					while (!(divTag = inputLine.charAt(i)).toString().equals(">")) {
						i++;
					}
					j = i;
					while (!(divTag = inputLine.charAt(j)).toString().equals("<")) {
						j++;
					}
					preco = inputLine.substring(i + 3, j);
					preco = preco.replace(".", "");
					preco = preco.replace(",", ".");
					preco = preco.trim();
					media += Double.parseDouble(preco);
					divisor++;
				}
			}
			retorno = media / divisor;
			retorno = round(retorno, 2);
			System.out.println("Média de preços: " + retorno);

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();
		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public void adicionaCPU(String nome, String fabricante, String socket) {
		try {
			CpuDao dao = new CpuDao();
			Cpu cpu = new Cpu();
			cpu.setNome(nome);
			cpu.setFabricante(fabricante);
			cpu.setSocket(socket);
			Double preco = pesquisaMediaPreco(nome);
			if (preco != null) cpu.setPreco(preco);
			dao.saveCpu(cpu);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void adicionaGPU(String nome, String fabricante) {

		try {
			Gpu gpu = new Gpu();
			gpu.setNome(nome);
			gpu.setFabricante(fabricante);
			Double preco = pesquisaMediaPreco(nome);
			if (preco != null) gpu.setPreco(preco);
			gpudao.saveGpu(gpu);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void adicionaRAM(String nome, String fabricante, String slot) {
		try {
			Ram ram = new Ram();
			ram.setNome(nome);
			ram.setFabricante(fabricante);
			ram.setSlot(slot);
			Double valor = (pesquisaMediaPreco(nome));
			if (valor != null) ram.setPreco(valor);
			ramdao.saveRam(ram);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void adicionaMobo(String nome, String fabricante, String socket, String slotRam, Integer totalRam,
			Integer qtdPcie) {
		PlacaMae mobo = new PlacaMae();
		mobo.setFabricante(fabricante);
		mobo.setNome(nome);
		mobo.setQtdPcie(qtdPcie);
		mobo.setSlotRam(slotRam);
		mobo.setSocket(socket);
		mobo.setTotalRam(totalRam);
		Double valor = pesquisaMediaPreco(nome);
		if (valor != null) mobo.setValor((valor));
		
		mobodao.saveMobo(mobo);
		
	}

	public void adicionaPsu(String nome, String fabricante, Integer potencia) {
		try {
			Psu fonte = new Psu();
			fonte.setFabricante(fabricante);
			fonte.setNome(nome);
			fonte.setPotencia(potencia);
			Double valor = pesquisaMediaPreco(nome);
			if (valor != null) fonte.setValor((valor));
			psudao.savePsu(fonte);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
