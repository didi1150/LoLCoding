package me.didi.utils;

import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class TaskManager {

	private static Plugin plugin;
	private static TaskManager instance;

	private TaskManager(Plugin plugin) {
		TaskManager.plugin = plugin;
	}

	public static void init(Plugin plugin) {
		if (instance != null)
			return;
		instance = new TaskManager(plugin);
	}

	/**
	 * Usage: repeatUntil(delay, period, duration, (task, count) -> { <br>
	 * blablalba if(count >= blabla)
	 * 
	 * });
	 */
	public <T> BukkitTask repeatUntil(long delay, long period, int howOften,
			BiConsumer<BukkitTask, AtomicLong> callback) {

		AtomicLong atomicLong = new AtomicLong(0);

		TaskHolder taskHolder = new TaskHolder();

		BukkitTask bukkitTask = new BukkitRunnable() {

			@Override
			public void run() {
				if (atomicLong.getAndIncrement() >= howOften) {
					this.cancel();
					return;
				}

				if (taskHolder.bukkitTask == null)
					return;
				callback.accept(taskHolder.bukkitTask, atomicLong);

			}
		}.runTaskTimer(plugin, delay, period);

		taskHolder.bukkitTask = bukkitTask;

		return bukkitTask;
	}

	/**
	 * Usage: repeatUntil(delay, period, duration, (task, count) -> { <br>
	 * blablalba if(count >= blabla)
	 * 
	 * });
	 */
	public <T> BukkitTask repeatWithCounter(long delay, long period, BiConsumer<BukkitTask, AtomicLong> callback) {

		AtomicLong atomicLong = new AtomicLong(0);

		TaskHolder taskHolder = new TaskHolder();

		BukkitTask bukkitTask = new BukkitRunnable() {

			@Override
			public void run() {
				atomicLong.incrementAndGet();

				if (taskHolder.bukkitTask == null)
					return;
				callback.accept(taskHolder.bukkitTask, atomicLong);

			}
		}.runTaskTimer(plugin, delay, period);

		taskHolder.bukkitTask = bukkitTask;

		return bukkitTask;
	}

	public BukkitTask repeat(long delay, long period, Consumer<BukkitTask> callback) {
		BukkitTask bukkitTask = getBukkitTask(delay, period, callback);

		return bukkitTask;

	}

	private BukkitTask getBukkitTask(long delay, long period, Consumer<BukkitTask> callback) {
		TaskHolder taskHolder = new TaskHolder();

		BukkitTask bukkitTask = new BukkitRunnable() {

			@Override
			public void run() {
				if (taskHolder.bukkitTask == null)
					return;
				callback.accept(taskHolder.bukkitTask);

			}
		}.runTaskTimer(plugin, delay, period);

		taskHolder.bukkitTask = bukkitTask;
		return bukkitTask;
	}

	public BukkitTask runTaskLater(long delay, Consumer<BukkitTask> callback) {
		TaskHolder taskHolder = new TaskHolder();

		BukkitTask bukkitTask = new BukkitRunnable() {

			@Override
			public void run() {
				if (taskHolder.bukkitTask == null)
					return;
				callback.accept(taskHolder.bukkitTask);

			}
		}.runTaskLater(plugin, delay);

		taskHolder.bukkitTask = bukkitTask;

		return bukkitTask;
	}

	/**
	 * PLEASE KEEP THINGS THREAD SAFE
	 */
	public BukkitTask runTaskAsync(Consumer<BukkitTask> callback) {
		TaskHolder taskHolder = new TaskHolder();

		BukkitTask bukkitTask = new BukkitRunnable() {

			@Override
			public void run() {
				if (taskHolder.bukkitTask == null)
					return;
				callback.accept(taskHolder.bukkitTask);

			}
		}.runTaskAsynchronously(plugin);

		taskHolder.bukkitTask = bukkitTask;

		return bukkitTask;
	}

	public static TaskManager getInstance() {
		if (instance == null)
			return null;
		return instance;
	}

	private class TaskHolder {
		public BukkitTask bukkitTask;
	}

}
