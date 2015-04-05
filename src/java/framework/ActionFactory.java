package framework;

import action.Action;

/**
 *
 * @author JJ
 */
public class ActionFactory
{
    public static Action getAction(String url, String actionPath) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        int pos = url.indexOf('.');
        String actionClassName = url.substring(1,pos); //1 to delete the /
        Class actionClass = Class.forName(actionPath+"."+actionClassName);
        Action action = (Action) actionClass.newInstance();
        return action;
    }

    public static Action getAction(String url) throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        return ActionFactory.getAction(url,"action");
    }
}
