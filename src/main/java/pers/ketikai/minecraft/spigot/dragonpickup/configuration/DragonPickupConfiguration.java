/*
 *    dragon-pickup
 *    Copyright (C) 2025  ketikai
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation, either version 3 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package pers.ketikai.minecraft.spigot.dragonpickup.configuration;

import lombok.Data;
import lombok.NonNull;
import team.idealstate.sugar.next.context.annotation.component.Configuration;
import team.idealstate.sugar.next.context.annotation.feature.Scope;

@Configuration(uri = "config.yml", release = "bundled:config.yml")
@Scope(Scope.PROTOTYPE)
@Data
public class DragonPickupConfiguration {

    @NonNull
    private final String hud;

    @NonNull
    private final String slot;

    @NonNull
    private final String function;
}
