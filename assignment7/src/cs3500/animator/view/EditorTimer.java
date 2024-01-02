package cs3500.animator.view;

import java.util.TimerTask;

/**
 * Timer task object for the editor view handles refreshing the animation panel every second.
 */
public class EditorTimer extends TimerTask {

  private final IEditorView editorView;

  /**
   * Constructs the animation timer objects with the visual view as a parameter.
   *
   * @param editorView the editor view that this timer is connected to
   */
  public EditorTimer(IEditorView editorView) {
    this.editorView = editorView;
  }

  @Override
  public void run() {
    editorView.refresh();
  }
}
