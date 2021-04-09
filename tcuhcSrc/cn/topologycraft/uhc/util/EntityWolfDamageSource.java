package cn.topologycraft.uhc.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class EntityWolfDamageSource extends EntityDamageSource
{
	public EntityWolfDamageSource(String damageTypeIn, EntityWolf damageSourceEntityIn)
	{
		super(damageTypeIn, damageSourceEntityIn);
	}

	public ITextComponent getDeathMessage(EntityLivingBase entityLivingBaseIn)
	{
		ITextComponent text = super.getDeathMessage(entityLivingBaseIn);
		if (this.damageSourceEntity != null)
		{
			EntityLivingBase owner = ((EntityWolf)this.damageSourceEntity).getOwner();
			if (owner != null)
			{
				text.appendSibling(new TextComponentString(String.format(
						" (Owner: %s)",
						((TextComponentString)owner.getDisplayName()).getText()
				)));
			}
		}
		return text;
	}
}
