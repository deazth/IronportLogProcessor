package amer.tm.ilp;
/** Localizable strings for {@link amer.tm.ilp}. */
class Bundle {
    /**
     * @return <i>Error Mappings</i>
     * @see ErrMapTopComponent
     */
    static String CTL_ErrMapAction() {
        return org.openide.util.NbBundle.getMessage(Bundle.class, "CTL_ErrMapAction");
    }
    /**
     * @return <i>Error Mappings</i>
     * @see ErrMapTopComponent
     */
    static String CTL_ErrMapTopComponent() {
        return org.openide.util.NbBundle.getMessage(Bundle.class, "CTL_ErrMapTopComponent");
    }
    /**
     * @return <i>Log Processor</i>
     * @see ilpTopComponent
     */
    static String CTL_ilpAction() {
        return org.openide.util.NbBundle.getMessage(Bundle.class, "CTL_ilpAction");
    }
    /**
     * @return <i>Log Processor</i>
     * @see ilpTopComponent
     */
    static String CTL_ilpTopComponent() {
        return org.openide.util.NbBundle.getMessage(Bundle.class, "CTL_ilpTopComponent");
    }
    /**
     * @return <i>This is a ErrMap window</i>
     * @see ErrMapTopComponent
     */
    static String HINT_ErrMapTopComponent() {
        return org.openide.util.NbBundle.getMessage(Bundle.class, "HINT_ErrMapTopComponent");
    }
    /**
     * @return <i>This is a ilp window</i>
     * @see ilpTopComponent
     */
    static String HINT_ilpTopComponent() {
        return org.openide.util.NbBundle.getMessage(Bundle.class, "HINT_ilpTopComponent");
    }
    private Bundle() {}
}
