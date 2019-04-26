/*
 * This file is part of Industrial Foregoing.
 *
 * Copyright 2018, Buuz135
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the
 * following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.buuz135.industrial.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBoneMeal;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FertilizerItem extends IFCustomItem {

    public FertilizerItem() {
        super("fertilizer", new Properties());
    }

    @Override
    public EnumActionResult onItemUse(ItemUseContext context) {
        EntityPlayer player = context.getPlayer();
        ItemStack itemstack = context.getItem();
        EnumFacing facing = context.getFace();
        BlockPos pos = context.getPos();
        World worldIn = context.getWorld();
        if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack)) return EnumActionResult.FAIL;
        if (ItemBoneMeal.applyBonemeal(itemstack, worldIn, pos, player)) {
            if (!worldIn.isRemote) {
                worldIn.playEvent(2005, pos, 0);
            }
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

}
