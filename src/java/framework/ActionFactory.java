package framework;

import action.Action;

/**
 *
 * @author JJ
 */
public class ActionFactory
{
    public static Action getAction(String url) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        int pos = url.indexOf('.');
        String actionClassName = url.substring(1,pos); //1 to delete the /
        Class actionClass = Class.forName("action."+actionClassName);
        Action action = (Action) actionClass.newInstance();
        return action;
    }
}
