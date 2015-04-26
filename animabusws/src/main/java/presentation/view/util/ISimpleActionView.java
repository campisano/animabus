package presentation.view.util;

import common.ICommand;

public interface ISimpleActionView {

	ICommand getCommand();

	void setCommand(ICommand command);

	String getError();

	void setError(String errorMessage);
}
