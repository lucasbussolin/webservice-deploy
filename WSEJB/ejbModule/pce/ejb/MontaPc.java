package pce.ejb;

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
import vo.personalcomputer.Computador;

@Stateless
public class MontaPc {

	private final GpuDao gpudao = new GpuDao();
	private final CpuDao cpudao = new CpuDao();
	private final PsuDao psudao = new PsuDao();
	private final RamDao ramdao = new RamDao();

	public Computador getGpuBuild(Double valor) {
		Computador retorno = null;
		try {
			retorno = new Computador();
			Double valorGpu = valor * 0.5;
			System.out.println("valor da gpu" + valorGpu);
			Double valorPsu = valor * 0.1;
			System.out.println("Valor da psu" + valorPsu);

			Gpu gpu = new Gpu();
			gpu = gpudao.getGpuByPrice(valorGpu);
			if (gpu != null) {
				valor -= gpu.getPreco();
				retorno.setValorTotal(retorno.getValorTotal() + gpu.getPreco());
			}

			Cpu cpu = new Cpu();
			cpu = cpudao.getCpuByPrice(valor);
			if (cpu != null) {
				retorno.setValorTotal(retorno.getValorTotal() + cpu.getPreco());
				PlacaMae mobo = new PlacaMae();
				Double valorMobo = valor * 0.5;
				MoboDao mobodao = new MoboDao();
				mobo = mobodao.getMoboByPriceAndSocket(valorMobo, cpu.getSocket());
				retorno.setPlacaMae(mobo);

				if (mobo != null) {
					retorno.setValorTotal(retorno.getValorTotal() + mobo.getValor());
					Ram ram = new Ram();
					Double valorRam = valor * 0.4;
					ram = ramdao.getRamByPriceAndSocket(valorRam, mobo.getSlotRam());
					if (ram != null) {
						valor -= ram.getPreco();
						retorno.setValorTotal(retorno.getValorTotal() + ram.getPreco());
						retorno.setRam(ram);
					}
				}
			}

			Psu psu = new Psu();
			psu = psudao.getPsuByPrice(valorPsu);
			if (psu != null) {
				valor -= psu.getValor();
				retorno.setValorTotal(retorno.getValorTotal() + psu.getValor());
			}

			retorno.setGpu(gpu);
			retorno.setCpu(cpu);
			retorno.setFonte(psu);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
}
