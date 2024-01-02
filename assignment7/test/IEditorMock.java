import cs3500.animator.view.IEditorView;
import java.awt.event.ActionListener;

public interface IEditorMock extends IEditorView, ActionListener {

  String getCommandPressed();

}
