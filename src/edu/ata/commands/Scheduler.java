package edu.ata.commands;

/**
 *
 * @author Joel Gallant
 */
public class Scheduler {

    public static void run(Command command) {
        command.run();
    }

    public static void runInThread(Command command) {
        new Thread(command).start();
    }

    public static void runInThread(Command command, int priority) {
        Thread t = new Thread(command);
        t.setPriority(priority);
        t.start();
    }

    public static void runSequencially(Command[] commands) {
        for (int x = 0; x < commands.length; ++x) {
            commands[x].run();
        }
    }

    public static void runSequenciallyInThread(final Command[] commands) {
        new Thread(new Runnable() {
            public void run() {
                runSequencially(commands);
            }
        }).start();
    }

    public static void runConcurrently(Command[] commands) {
        Thread[] threads = new Thread[commands.length];
        for (int x = 0; x < commands.length; ++x) {
            threads[x] = new Thread(commands[x]);
            threads[x].start();
        }
        for (int x = 0; x < threads.length; ++x) {
            try {
                threads[x].join();
            } catch (InterruptedException ex) {
            }
        }
    }

    public static void runConcurrentlyInThread(final Command[] commands) {
        new Thread(new Runnable() {
            public void run() {
                runConcurrently(commands);
            }
        }).start();
    }
}
