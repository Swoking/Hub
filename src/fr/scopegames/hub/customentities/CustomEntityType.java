package fr.scopegames.hub.customentities;

import fr.scopegames.hub.customentities.RideableChicken;
import fr.scopegames.hub.customentities.RideableCow;
import fr.scopegames.hub.customentities.RideableCreeper;
import fr.scopegames.hub.customentities.RideableSheep;
import fr.scopegames.hub.customentities.RideableSpider;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import net.minecraft.server.v1_9_R2.BiomeBase;
import net.minecraft.server.v1_9_R2.EntityChicken;
import net.minecraft.server.v1_9_R2.EntityCow;
import net.minecraft.server.v1_9_R2.EntityCreeper;
import net.minecraft.server.v1_9_R2.EntityInsentient;
import net.minecraft.server.v1_9_R2.EntitySheep;
import net.minecraft.server.v1_9_R2.EntitySpider;
import net.minecraft.server.v1_9_R2.EntityTypes;
import org.bukkit.entity.EntityType;

public enum CustomEntityType {
    CREEPER("Creeper", 50, EntityType.CREEPER, EntityCreeper.class, RideableCreeper.class),
    SPIDER("Spider", 52, EntityType.SPIDER, EntitySpider.class, RideableSpider.class),
    SHEEP("Sheep", 91, EntityType.SHEEP, EntitySheep.class, RideableSheep.class),
    COW("Cow", 92, EntityType.COW, EntityCow.class, RideableCow.class),
    CHICKEN("Chicken", 93, EntityType.CHICKEN, EntityChicken.class, RideableChicken.class);
    
    private String name;
    private int id;
    private EntityType entityType;
    private Class<? extends EntityInsentient> nmsClass;
    private Class<? extends EntityInsentient> customClass;

    private CustomEntityType(String name, int id, EntityType entityType, Class<? extends EntityInsentient> nmsClass, Class<? extends EntityInsentient> customClass) {
        this.name = name;
        this.id = id;
        this.entityType = entityType;
        this.nmsClass = nmsClass;
        this.customClass = customClass;
    }

    public String getName() {
        return this.name;
    }

    public int getID() {
        return this.id;
    }

    public EntityType getEntityType() {
        return this.entityType;
    }

    public Class<? extends EntityInsentient> getNMSClass() {
        return this.nmsClass;
    }

    public Class<? extends EntityInsentient> getCustomClass() {
        return this.customClass;
    }

    public static void registerEntities() {
        BiomeBase[] biomes;
        for (CustomEntityType entity : CustomEntityType.values()) {
            CustomEntityType.a(entity.getCustomClass(), entity.getName(), entity.getID());
        }
        try {
            biomes = (BiomeBase[])CustomEntityType.getPrivateStatic(BiomeBase.class, "biomes");
        }
        catch (Exception exc) {
            return;
        }
        for (BiomeBase biomeBase : biomes) {
            if (biomeBase == null) break;
            for (String field : new String[]{"u", "v", "w", "x"}) {
                try {
                    Field list = BiomeBase.class.getDeclaredField(field);
                    list.setAccessible(true);
                    List mobList = (List)list.get((Object)biomeBase);
                    for (BiomeBase.BiomeMeta meta : mobList) {
                        for (CustomEntityType entity2 : CustomEntityType.values()) {
                            if (!entity2.getNMSClass().equals((Object)meta.b)) continue;
                            meta.b = entity2.getCustomClass();
                        }
                    }
                    continue;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void unregisterEntities() {
        BiomeBase[] biomes;
        for (CustomEntityType entity2 : CustomEntityType.values()) {
            try {
                ((Map)CustomEntityType.getPrivateStatic(EntityTypes.class, "d")).remove(entity2.getCustomClass());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            try {
                ((Map)CustomEntityType.getPrivateStatic(EntityTypes.class, "f")).remove(entity2.getCustomClass());
                continue;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        for (CustomEntityType entity2 : CustomEntityType.values()) {
            try {
                CustomEntityType.a(entity2.getNMSClass(), entity2.getName(), entity2.getID());
                continue;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            biomes = (BiomeBase[])CustomEntityType.getPrivateStatic(BiomeBase.class, "biomes");
        }
        catch (Exception exc) {
            return;
        }
        for (BiomeBase biomeBase : biomes) {
            if (biomeBase == null) break;
            for (String field : new String[]{"at", "au", "av", "aw"}) {
                try {
                    Field list = BiomeBase.class.getDeclaredField(field);
                    list.setAccessible(true);
                    List mobList = (List)list.get((Object)biomeBase);
                    for (BiomeBase.BiomeMeta meta : mobList) {
                        for (CustomEntityType entity3 : CustomEntityType.values()) {
                            if (!entity3.getCustomClass().equals((Object)meta.b)) continue;
                            meta.b = entity3.getNMSClass();
                        }
                    }
                    continue;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static Object getPrivateStatic(Class clazz, String f) throws Exception {
        Field field = clazz.getDeclaredField(f);
        field.setAccessible(true);
        return field.get(null);
    }

    private static void a(Class paramClass, String paramString, int paramInt) {
        try {
            ((Map)CustomEntityType.getPrivateStatic(EntityTypes.class, "c")).put(paramString, paramClass);
            ((Map)CustomEntityType.getPrivateStatic(EntityTypes.class, "d")).put(paramClass, paramString);
            ((Map)CustomEntityType.getPrivateStatic(EntityTypes.class, "e")).put(paramInt, paramClass);
            ((Map)CustomEntityType.getPrivateStatic(EntityTypes.class, "f")).put(paramClass, paramInt);
            ((Map)CustomEntityType.getPrivateStatic(EntityTypes.class, "g")).put(paramString, paramInt);
        }
        catch (Exception exc) {
            // empty catch block
        }
    }
}