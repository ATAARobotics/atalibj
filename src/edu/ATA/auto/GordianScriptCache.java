package edu.ATA.auto;

/**
 * The {@link GordianScriptCache} class is used as a singleton cache for storing
 * the 'selected' Gordian (Team 4334's scripting language) script.
 *
 * <p> Can be regarded as "thread safe" where it is safe to assume such.
 *
 * @author Joel Gallant
 */
public final class GordianScriptCache {

    private static GordianScriptCache instance;
    private final Object lock = new Object();
    private String script = "";

    /**
     * Returns the singleton instance of {@link GordianScriptCache}. This is,
     * and should be the only instance ever created of
     * {@link GordianScriptCache}. It uses class synchronization to ensure it is
     * constructed correctly. (synchronized lazy initialization)
     *
     * @return
     */
    public static GordianScriptCache getInstance() {
        synchronized (GordianScriptCache.class) {
            if (instance == null) {
                instance = new GordianScriptCache();
            }
        }
        return instance;
    }

    private GordianScriptCache() {
    }

    /**
     * This method sets the current script, that can be requested using
     * {@link GordianScriptCache#getScript() getScript()}. It is reasonable to
     * assume that if this method is only ever used once, the argument passed to
     * it the first time will be what
     * {@link GordianScriptCache#getScript() getScript()} will return.
     *
     * <p> {@code script} can not be null. If you want to 'delete' the current
     * script, use an empty string.
     *
     * @param script Gordian script to use
     */
    public void setScript(String script) {
        if (script == null) {
            throw new NullPointerException();
        }
        synchronized (lock) {
            this.script = script;
        }
    }

    /**
     * Returns the script last set by
     * {@link GordianScriptCache#setScript(java.lang.String) setScript(String)}.
     * If it has never been set, this will return an empty string (not null).
     *
     * @return script last set (or empty statement)
     */
    public String getScript() {
        synchronized (lock) {
            return script;
        }
    }

    /**
     * Returns a {@link GordianAutonomous} object representing
     * {@link GordianScriptCache#getScript() getScript()}. Will run the
     * currently selected script as an autonomous mode.
     *
     * @param name name to assign the autonomous mode (see
     * {@link AutonomousMode#AutonomousMode(java.lang.String)})
     * @return autonomous mode that runs the current autonomous mode
     */
    public GordianAutonomous getScriptAutonomous(String name) {
        return new GordianAutonomous(name, getScript());
    }
}
