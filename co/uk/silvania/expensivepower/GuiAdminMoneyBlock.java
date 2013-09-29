package co.uk.silvania.expensivepower;

import org.lwjgl.opengl.GL11;

import ic2.core.block.generator.container.ContainerBaseGenerator;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiAdminMoneyBlock extends GuiContainer {
	
	public ContainerBaseGenerator container;
	public String name;
	public String inv;
	private static final ResourceLocation background = new ResourceLocation("expensivepower", "textures/gui/GUIAdminMoney");
	
	

	public GuiAdminMoneyBlock(ContainerBaseGenerator container) {
		super(container);

		this.container = container;
		this.name = "Name!";
		this.inv = StatCollector.translateToLocal("container.inventory");
	}

	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString(this.name,  (this.xSize - this.fontRenderer.getStringWidth(this.name)) / 2, 6, 4210752);
		this.fontRenderer.drawString(this.inv, 8, this.ySize - 96 + 2, 4210752);		
	}
	
	protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.func_110434_K().func_110577_a(background);
		int j = (this.width - this.xSize) / 2;
		int k = (this.height - this.ySize) / 2;
		drawTexturedModalRect(j, k, 0, 0, this.xSize, this.ySize);
		
		if (this.container.tileEntity.fuel > 0) {
			int l = this.container.tileEntity.gaugeFuelScaled(12);
			drawTexturedModalRect(j + 66, k + 36 + 12 - l, 176, 12 - l, 14, l + 2);
		}
		
		int i1 = this.container.tileEntity.gaugeStorageScaled(24);
		drawTexturedModalRect(j + 94, k + 35, 176, 14, i1, 17);
	}
}
