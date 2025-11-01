package commons;

import org.openqa.selenium.WebDriver;
import pageUIs.pageUIsCommon.commonUIs.BaseElementUI;
import pageUIs.pageUIsCommon.loginUIs.LoginPageUI;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class BaseElement extends BasePage {
    WebDriver driver;

    public BaseElement(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToTextboxByName(String valueToSendKey, String name) {
        waitForElementVisible(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_NAME, name);
        clearInputByJS(driver,BaseElementUI.DYNAMIC_TEXTBOX_BY_NAME, name);
        sendkeyToElement(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_NAME, valueToSendKey, name);
    }

    public void enterToTextboxByLabel(String valueToSendKey, String label) {
        waitForElementVisible(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_LABEL, label);
        clearInputByJS(driver,BaseElementUI.DYNAMIC_TEXTBOX_BY_LABEL, label);
        sendkeyToElement(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_LABEL, valueToSendKey, label);
    }

    public void enterToCanlenderTextboxByLabel(String valueToSendKey, String label) {
        waitForElementVisible(driver, BaseElementUI.DYNAMIC_CANLENDER_TEXTBOX_BY_LABEL, label);
        clearInputByJS(driver,BaseElementUI.DYNAMIC_CANLENDER_TEXTBOX_BY_LABEL, label);
        sendkeyToElement(driver, BaseElementUI.DYNAMIC_CANLENDER_TEXTBOX_BY_LABEL, valueToSendKey, label);
    }
    public void enterToTextareaByLabel(String valueToSendKey, String label) {
        waitForElementVisible(driver, BaseElementUI.DYNAMIC_TEXTAREA_BY_LABEL, label);
        clearInputByJS(driver,BaseElementUI.DYNAMIC_TEXTAREA_BY_LABEL, label);
        sendkeyToElement(driver, BaseElementUI.DYNAMIC_TEXTAREA_BY_LABEL, valueToSendKey, label);
    }

    public void selectToDropdownByLabel(String itemTextExpected, String label) {
        waitForElementClickable(driver, BaseElementUI.DYNAMIC_DROPDOWN_PARENT_BY_LABEL, label);
        selectItemDropdown(driver, BaseElementUI.DYNAMIC_DROPDOWN_PARENT_BY_LABEL, BaseElementUI.DYNAMIC_DROPDOWN_CHILDREN_ITEM_BY_LABEL, itemTextExpected, label);

    }
    public void scrollToDropdownOnTop(String label){
        scrollToElementOnDown(driver,BaseElementUI.DYNAMIC_DROPDOWN_PARENT_BY_LABEL, label);
    }
    public void scrollToTextboxOnDown(String name){
        scrollToElementOnDown(driver,BaseElementUI.DYNAMIC_TEXTBOX_BY_NAME,name);
    }
    public void scrollButtonOnTopByName(String name){
        scrollToElementOnDown(driver,BaseElementUI.BUTTON_BY_NAME, name);
    }


    public void waitForSpinnerIconInvisible() {
        waitForListElementInvisible(driver, BaseElementUI.SPINNER_ICON);
    }

    public String getValueInTextBoxByLabel(String label) {
        waitForElementVisible(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_LABEL, label);
        return getElementAttribute(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_LABEL, "_value", label);
    }

    public String getValueInTextBoxByName(String name){
        waitForElementVisible(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_NAME,name);
        return getElementAttribute(driver, BaseElementUI.DYNAMIC_TEXTBOX_BY_NAME, "value", name);
    }

    public String getToday() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return today.format(formatter);
    }
    public String getTimeNow(){
        Date now = new Date();

        // hh = 12h format, mm = phút, a = AM/PM
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        String currentTime = sdf.format(now);
        return currentTime;
    }
    public String getUser(){
        waitForElementVisible(driver,BaseElementUI.DISPLAY_NAME);
        return getElementText(driver, BaseElementUI.DISPLAY_NAME);
    }
    public void clickToAddButtonAdminByLabel(String label) {
        waitForElementClickable(driver, BaseElementUI.DYNAMIC_ADD_BUTTON_ADMIN_BY_LABEL, label);
        clickToElement(driver, BaseElementUI.DYNAMIC_ADD_BUTTON_ADMIN_BY_LABEL, label);
    }
    public void clickToAddButtonPimByLabel(String label) {
        waitForElementClickable(driver, BaseElementUI.DYNAMIC_ADD_BUTTON_PIM_BY_LABEL, label);
        clickToElement(driver, BaseElementUI.DYNAMIC_ADD_BUTTON_PIM_BY_LABEL, label);
    }



        public void clickToSaveButtonByLabel(String label) {
        waitForElementClickable(driver, BaseElementUI.DYNAMIC_SAVE_BUTTON_BY_LABEL, label);
        clickToElementByJS(driver, BaseElementUI.DYNAMIC_SAVE_BUTTON_BY_LABEL, label);
    }
    public void clickButtonSave (){
        waitForElementClickable(driver, BaseElementUI.SAVE_BUTTON);
        clickToElement(driver, BaseElementUI.SAVE_BUTTON);

    }

    public boolean isSuccessMessageDisplayed(String messageContent) {
        waitForElementVisible(driver, BaseElementUI.DYNAMIC_SUCCESS_MESSAGE, messageContent);
        return isElementDisplayed(driver, BaseElementUI.DYNAMIC_SUCCESS_MESSAGE, messageContent);
    }

    public boolean isSuccessMessageNotDisplayed(String messageContent) {
        return isElementUndisplayed(driver, BaseElementUI.DYNAMIC_SUCCESS_MESSAGE, messageContent);
    }

    public void enterToTextArea(String valueToSenKey) {
        waitForElementVisible(driver, BaseElementUI.TEXTAREA);
        sendkeyToElement(driver, BaseElementUI.TEXTAREA, valueToSenKey);

    }

    public String getValueInCanlenderTextBox(String label) {
        waitForElementVisible(driver, BaseElementUI.DYNAMIC_CANLENDER_TEXTBOX_BY_LABEL, label);
        return getElementAttribute(driver, BaseElementUI.DYNAMIC_CANLENDER_TEXTBOX_BY_LABEL, "value", label);
    }

    public String getValueInDropdownByLabel(String label) {
        waitForElementVisible(driver, BaseElementUI.DYNAMIC_VALUE_DROPDOWN_BY_LABEL, label);
        return getElementText(driver, BaseElementUI.DYNAMIC_VALUE_DROPDOWN_BY_LABEL, label);
    }

    public boolean isValueDisplayedAtColumnName(String columnName, String rowIndex, String rowValue) {
        int columnIndex = getListElementSize(driver, BaseElementUI.DYNAMIC_INDEX_BY_COLUMN_NAME, columnName) +1;
        return isElementDisplayed(driver, BaseElementUI.DYNAMIC_ROW_VALUE_BY_COLUMN_INDEX_ROW_INDEX, rowIndex, String.valueOf(columnIndex), rowValue);
    }
    public void enterToTextboxInTable(String columnName, String rowIndex, String valueToSenkey){
        int columnIndex = getListElementSize(driver, BaseElementUI.DYNAMIC_INDEX_BY_COLUMN_NAME_IN_TABLE, columnName)+1;
        clearInputByJS(driver,BaseElementUI.DYNAMIC_TEXTBOX_BY_COLUMN_INDEX_ROW_INDEX_IN_TABLE, rowIndex, String.valueOf(columnIndex));
        sendkeyToElement(driver,BaseElementUI.DYNAMIC_TEXTBOX_BY_COLUMN_INDEX_ROW_INDEX_IN_TABLE,valueToSenkey,rowIndex, String.valueOf(columnIndex));

    }
    public String getValueInTextboxInTable(String columnName, String rowIndex){
        int columnIndex = getListElementSize(driver, BaseElementUI.DYNAMIC_INDEX_BY_COLUMN_NAME_IN_TABLE, columnName)+1;
        return getElementText(driver,BaseElementUI.DYNAMIC_TEXTBOX_BY_COLUMN_INDEX_ROW_INDEX_IN_TABLE,rowIndex, String.valueOf(columnIndex));
    }
    public void selectItemInDropdownInTable(String columnName, String rowIndex, String value){
        int columnIndex = getListElementSize(driver, BaseElementUI.DYNAMIC_INDEX_BY_COLUMN_NAME_IN_TABLE, columnName)+1 ;
        selectItemDropdown(driver,BaseElementUI.DYNAMIC_DROPDOWN_PARENT_BY_LABEL_IN_TABLE, BaseElementUI.DYNAMIC_DROPDOWN_CHILDREN_ITEM_BY_LABEL_IN_TABlE,value,rowIndex,String.valueOf(columnIndex),rowIndex,String.valueOf(columnIndex));
    }
    public void searchAndSelectInCombobox(String columnName, String rowIndex, String valueToSearch, String valueToSelect){
        int columnIndex = getListElementSize(driver, BaseElementUI.DYNAMIC_INDEX_BY_COLUMN_NAME_IN_TABLE, columnName)+1;
        clearInputByJS(driver,BaseElementUI.DYNAMIC_TEXTBOX_BY_COLUMN_INDEX_ROW_INDEX_IN_TABLE, rowIndex, String.valueOf(columnIndex));
        sendkeyToElement(driver,BaseElementUI.DYNAMIC_TEXTBOX_BY_COLUMN_INDEX_ROW_INDEX_IN_TABLE,valueToSearch,rowIndex, String.valueOf(columnIndex));
        selectItemInCombobox(driver, BaseElementUI.DYNAMIC_COMBOBOX_CHILDREN_ITEM_BY_LABEL_IN_TABlE,rowIndex,String.valueOf(columnIndex),valueToSelect);
    }
    public void searchAndSelectInComboboxByLabel( String valueToSearch, String valueToSelect, String label){
        enterToTextboxByLabel(valueToSearch,label);
        selectItemInCombobox(driver, BaseElementUI.DYNAMIC_OPTION_COBOBOX,valueToSelect);
    }
    public String getCellValueInTable(String columnName, String rowIndex){
        int columnIndex = getListElementSize(driver, BaseElementUI.DYNAMIC_INDEX_BY_COLUMN_NAME_IN_TABLE, columnName)+1;
        String cellValue=getElementText(driver,BaseElementUI.CELL_VALUE_IN_TABLE,rowIndex,String.valueOf(columnIndex));
        return cellValue;
    }
    public boolean isItemDropdownDisplayed(String itemCheck,String label){
        waitForElementClickable(driver,BaseElementUI.DYNAMIC_DROPDOWN_PARENT_BY_LABEL,label);
        clickToElement(driver,BaseElementUI.DYNAMIC_DROPDOWN_PARENT_BY_LABEL,label);
        return isElementDisplayed(driver,BaseElementUI.DYNAMIC_VALUE_ITEM_DROPDOWN_BY_NAME,label,itemCheck);
    }
    public void clickToEditButton(String columnName, String rowIndex) {
        int columnIndex = getListElementSize(driver, BaseElementUI.DYNAMIC_INDEX_BY_COLUMN_NAME, columnName) + 1;
        scrollToElementOnDown(driver, BaseElementUI.DYNAMIC_EDIT_ICON_BY_COLUMN_INDEX_ROW_INDEX,rowIndex,String.valueOf(columnIndex));
        waitForElementClickable(driver, BaseElementUI.DYNAMIC_EDIT_ICON_BY_COLUMN_INDEX_ROW_INDEX, rowIndex, String.valueOf(columnIndex));
        clickToElement(driver,BaseElementUI.DYNAMIC_EDIT_ICON_BY_COLUMN_INDEX_ROW_INDEX,rowIndex,String.valueOf(columnIndex));
    }
    public String getErrorMessage(WebDriver driver){
        waitForElementVisible(driver,BaseElementUI.MESSAGE_ERROR);
        return getElementText(driver,BaseElementUI.MESSAGE_ERROR);
    }
    public void clickToRadioByValue(String gender) {
        clickToElementByJS(driver, BaseElementUI.GENDER_RADIO, gender);
    }
    public boolean isRadioCheckedByValue(String value){
        return isElementSelected(driver, BaseElementUI.DYNAMIC_RADIO_BUTTON_BY_TEXT,value);

    }
    public void clickToggle(){
        waitForSpinnerIconInvisible();
        waitForElementClickable(driver, BaseElementUI.TOGGLE);
        clickToElement(driver,BaseElementUI.TOGGLE);
    }
    public void logout(){
        waitForElementClickable(driver, BaseElementUI.AVATA);
        clickToElementByJS(driver, BaseElementUI.AVATA);
        waitForElementClickable(driver,BaseElementUI.LOGOUT);
        clickToElementByJS(driver, BaseElementUI.LOGOUT);
    }
    public boolean checkTextboxDisable( String name){
        waitForElementVisible(driver,BaseElementUI.DYNAMIC_TEXTBOX_BY_LABEL,name);
        return isElementEnable(driver,BaseElementUI.DYNAMIC_TEXTBOX_BY_LABEL,name);
    }
    public void scrollCalanderOnDown(String label){
        scrollToElementOnDown(driver, BaseElementUI.DYNAMIC_CANLENDER_TEXTBOX_BY_LABEL, label);
    }
    public void scrollCalanderOnTop(String label){
        scrollToElementOnTop(driver, BaseElementUI.DYNAMIC_CANLENDER_TEXTBOX_BY_LABEL, label);
    }
    public void clickCanlanderIconByLabel(String label){
        waitForElementClickable(driver,BaseElementUI.DYNAMIC_CANLENDER_TEXTBOX_BY_LABEL,label);
        clickToElement(driver,BaseElementUI.DYNAMIC_CANLENDER_TEXTBOX_BY_LABEL,label);

    }
    public void isButtonInCanalenerDisplay(String nameButton){
        waitForElementVisible(driver,BaseElementUI.DYNAMIC_CALENDER_BUTTON, nameButton);
        isElementDisplayed(driver, BaseElementUI.DYNAMIC_CALENDER_BUTTON,nameButton);
    }
    public void clickButtonInCanlender(String nameButton){
        waitForElementClickable(driver,BaseElementUI.DYNAMIC_CALENDER_BUTTON, nameButton);
        clickToElement(driver,BaseElementUI.DYNAMIC_CALENDER_BUTTON, nameButton);
    }
    public String getColorOfTodayDay(String day){
        waitForElementVisible(driver,BaseElementUI.DYNAMIC_TODAY,day);
        return getElementCssValue(driver,BaseElementUI.DYNAMIC_TODAY,"background-color",day);

    }
    public String getColorOfFocusDay(String day){
        waitForElementVisible(driver,BaseElementUI.DYNAMIC_DAY_IN_CALENDER_AFTER_SELECTED,day);
        return getElementCssValue(driver,BaseElementUI.DYNAMIC_DAY_IN_CALENDER_AFTER_SELECTED,"background-color",day);

    }
    public void selectRadioButtonByValue(String label){
//        waitForElementClickable(driver, BaseElementUI.DYNAMIC_RADIO_BUTTON_BY_TEXT,label);
//        clickToElement(driver, BaseElementUI.DYNAMIC_RADIO_BUTTON_BY_TEXT,label);
        clickToElementByJS(driver, BaseElementUI.DYNAMIC_RADIO_BUTTON_BY_TEXT,label);

    }
    public boolean isRadioButtonSelectedByValue(String label){
        return isElementSelected(driver, BaseElementUI.DYNAMIC_RADIO_BUTTON_BY_TEXT,label);

    }
    public List<String> getListActualLabel() {
        return getListElementText(driver, BaseElementUI.DYNAMIC_NAME_LABEL);
    }

    public List<String> getListActualPlaceholder() {
        List<String> list = getListElementAttribute(driver, BaseElementUI.TEXTBOX_PLACEHOLDER, "placeholder");
        list.remove("Search");
        return list;
    }

    public String getActualPlaceholder() {
        return getElementAttribute(driver, BaseElementUI.TEXTAREA_PLACEHOLDER, "placeholder");
    }

    public List<String> getListListErrorMessageColer() {
        List<String> list = getListElementCssValue(driver, BaseElementUI.ERROR_MESSAGE, "color");
        return list;
    }
    public void isErrorMessageDisplay(String errorMessage) {
        this.waitForElementVisible(driver, BaseElementUI.DYNAMIC_ERROR_MESSAGE, errorMessage);
        this.isElementDisplayed(driver, BaseElementUI.DYNAMIC_ERROR_MESSAGE, errorMessage);
    }
    public boolean isErrorMessageDisplayTest(String errorMessage) {
        this.waitForElementVisible(driver, BaseElementUI.DYNAMIC_ERROR_MESSAGE, errorMessage);
        return this.isElementDisplayed(driver, BaseElementUI.DYNAMIC_ERROR_MESSAGE, errorMessage);
    }
    public boolean isErrorMsgDisplayedByName(String name, String msg){
        this.waitForElementVisible(driver, BaseElementUI.DYNAMIC_ERROR_MESSAGE_BY_NAME, name, msg);
        return this.isElementDisplayed(driver, BaseElementUI.DYNAMIC_ERROR_MESSAGE_BY_NAME, name, msg);
    }
    public boolean isErrorMsgDisplayedByLabel(String label, String msg){
        this.waitForElementVisible(driver, BaseElementUI.DYNAMIC_ERROR_MESSAGE_BY_LABEL, label,msg);
        return this.isElementDisplayed(driver, BaseElementUI.DYNAMIC_ERROR_MESSAGE_BY_LABEL, label,msg);
    }
    public String colorOfButton(String nameButton){
        return getElementCssValue(driver,BaseElementUI.DYNAMIC_BUTTON,"background-color", nameButton);
    }
    public String colorTextInButton(String nameButton){
        return getElementCssValue(driver,BaseElementUI.DYNAMIC_BUTTON,"color", nameButton);
    }
    public boolean checkStatusOfToogle(){
        return isElementSelected(driver,BaseElementUI.TOGGLE);
    }
    public boolean isDefaultAavataDisplay(){
        return isElementDisplayed(driver, BaseElementUI.DEFAULT_AVATA);
    }
    public boolean isAavataAfterUploadDisplay(){
        return isElementDisplayed(driver, BaseElementUI.AVATA_AFTER_UPLOAD);
    }
    public void clickCancelButton(){
        waitForElementClickable(driver, BaseElementUI.CANCEL_BUTTON);
        clickToElement(driver, BaseElementUI.CANCEL_BUTTON);
    }
    public void clickButtonByName(String nameButton){
        waitForElementClickable(driver, BaseElementUI.BUTTON_BY_NAME,nameButton);
        clickToElement(driver, BaseElementUI.BUTTON_BY_NAME,nameButton);
    }
    public void clickButtonByJSAndName(String nameButton){
        waitForElementClickable(driver, BaseElementUI.BUTTON_BY_NAME,nameButton);
        clickToElementByJS(driver, BaseElementUI.BUTTON_BY_NAME,nameButton);
    }
    public void clickAddRowButton(){
        waitForElementClickable(driver, BaseElementUI.ADD_ROW_BUTTON);
        clickToElement(driver, BaseElementUI.ADD_ROW_BUTTON);
    }
    public void clickEditIcon(){
        waitForElementClickable(driver, BaseElementUI.EDIT_ICON_BY_INDEX);
        clickToElement(driver, BaseElementUI.EDIT_ICON_BY_INDEX);
    }

    public void clickDeleteIcon(){
        waitForElementClickable(driver, BaseElementUI.DELETE_ICON_BY_INDEX);
        clickToElement(driver, BaseElementUI.DELETE_ICON_BY_INDEX);
    }
    public String getDisplayname(){
        this.waitForElementVisible(driver, BaseElementUI.DISPLAY_NAME);
        return getElementText(driver, BaseElementUI.DISPLAY_NAME);
    }


}