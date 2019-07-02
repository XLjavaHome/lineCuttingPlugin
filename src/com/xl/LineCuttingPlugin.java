package com.xl;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import com.intellij.ui.popup.PopupFactoryImpl;
import org.jetbrains.annotations.NotNull;

/**
 * Created with 徐立.
 *
 * @author 徐立
 * @date 2019-07-02
 * @time 11:07
 * To change this template use File | Settings | File Templates.
 */
public class LineCuttingPlugin extends EditorAction {
    public LineCuttingPlugin() {
        super(new LineCuttingPlugin.Handler());
    }
    
    private static class Handler extends EditorWriteActionHandler {
        @Override
        public boolean executeInCommand(@NotNull Editor editor, DataContext dataContext) {
            showPopupBalloon(editor, "这是汽包");
            return super.executeInCommand(editor, dataContext);
        }
        
        /**
         * 显示汽包
         *
         * @param mEditor
         * @param result
         */
        private void showPopupBalloon(final Editor mEditor, final String result) {
            ApplicationManager.getApplication().invokeLater(() -> {
                mEditor.putUserData(PopupFactoryImpl.ANCHOR_POPUP_POSITION, null);
                JBPopupFactory factory = JBPopupFactory.getInstance();
                factory.createHtmlTextBalloonBuilder(result, null, new JBColor(Gray._242, Gray._0), null).createBalloon()
                       .show(factory.guessBestPopupLocation(mEditor), Balloon.Position.below);
            });
        }
    }
    //1.弹出提示框
    //2.提示框的默认值是、
    //3.根据提示框的默认值进行切割
}
