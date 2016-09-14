package fr.scopegames.hub.customentities;

import fr.scopegames.hub.mount.MountManager;
import java.lang.reflect.Field;
import java.util.List;
import net.minecraft.server.v1_9_R2.Entity;
import net.minecraft.server.v1_9_R2.EntityChicken;
import net.minecraft.server.v1_9_R2.EntityHuman;
import net.minecraft.server.v1_9_R2.EntityLiving;
import net.minecraft.server.v1_9_R2.World;
import org.bukkit.entity.Player;

public class RideableChicken
extends EntityChicken {
    private Player rider;

    public RideableChicken(World world, Player rider) {
        super(world);
        this.rider = rider;
    }

    public void g(float sideMot, float forMot) {
        if (MountManager.shouldDie((EntityLiving)this, this.rider)) {
            return;
        }
        for (Entity entity : this.passengers) {
            if (!(entity instanceof EntityHuman)) {
                return;
            }
            EntityHuman passenger = (EntityHuman)entity;
            this.lastYaw = this.yaw = passenger.yaw;
            this.pitch = passenger.pitch * 0.5f;
            this.setYawPitch(this.yaw, this.pitch);
            this.aP = this.aN = this.yaw;
            this.P = 0.5f;
            sideMot = passenger.be;
            forMot = passenger.bf;
            if (forMot <= 0.0f) {
                forMot *= 0.25f;
            }
            sideMot *= 0.75f;
            float speed = (float)MountManager.mountSpeed;
            this.k(speed);
            Field jump = null;
            try {
                jump = EntityLiving.class.getDeclaredField("bd");
                jump.setAccessible(true);
                if (jump != null && this.onGround && jump.getBoolean((Object)passenger)) {
                    double jumpHeight;
                    this.motY = jumpHeight = 0.5;
                }
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
            super.g(sideMot, forMot);
        }
    }
}