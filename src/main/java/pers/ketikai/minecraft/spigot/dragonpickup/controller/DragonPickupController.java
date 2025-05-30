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

package pers.ketikai.minecraft.spigot.dragonpickup.controller;

import pers.ketikai.minecraft.spigot.dragonpickup.api.configuration.ConfigurationHolder;
import pers.ketikai.minecraft.spigot.dragonpickup.configuration.DragonPickupConfiguration;
import team.idealstate.sugar.logging.Log;
import team.idealstate.sugar.next.command.Command;
import team.idealstate.sugar.next.command.CommandResult;
import team.idealstate.sugar.next.command.annotation.CommandHandler;
import team.idealstate.sugar.next.context.Bean;
import team.idealstate.sugar.next.context.Context;
import team.idealstate.sugar.next.context.annotation.component.Controller;
import team.idealstate.sugar.next.context.annotation.feature.Autowired;
import team.idealstate.sugar.next.context.annotation.feature.Named;
import team.idealstate.sugar.next.context.aware.ContextAware;
import team.idealstate.sugar.validate.Validation;
import team.idealstate.sugar.validate.annotation.NotNull;

@Named("dragon-pickup")
@Controller
public class DragonPickupController implements Command, ContextAware {

    @CommandHandler
    @NotNull
    public CommandResult reload() {
        try {
            Bean<DragonPickupConfiguration> bean = context.getBean(DragonPickupConfiguration.class);
            Validation.notNull(bean, "未能获取到配置 Bean。");
            assert bean != null;
            configurationHolder.setConfiguration(bean.getInstance());
        } catch (Throwable e) {
            Log.error(e);
            return CommandResult.failure("未能完成配置重载，错误信息请查看日志输出。");
        }
        return CommandResult.success("已完成配置重载");
    }

    private volatile Context context;

    @Override
    public void setContext(@NotNull Context context) {
        this.context = context;
    }

    private volatile ConfigurationHolder configurationHolder;

    @Autowired
    public void setConfigurationHolder(@NotNull ConfigurationHolder configurationHolder) {
        this.configurationHolder = configurationHolder;
    }
}
