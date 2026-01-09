package com.mecanotun.mobile.databinding;
import com.mecanotun.mobile.R;
import com.mecanotun.mobile.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityBookAppointmentBindingImpl extends ActivityBookAppointmentBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tv_title, 4);
        sViewsWithIds.put(R.id.tv_mechanic_label, 5);
        sViewsWithIds.put(R.id.spinner_mechanic, 6);
        sViewsWithIds.put(R.id.tv_vehicle_label, 7);
        sViewsWithIds.put(R.id.spinner_vehicle, 8);
        sViewsWithIds.put(R.id.til_note, 9);
    }
    // views
    @NonNull
    private final android.widget.ScrollView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers
    private androidx.databinding.InverseBindingListener etNoteandroidTextAttrChanged = new androidx.databinding.InverseBindingListener() {
        @Override
        public void onChange() {
            // Inverse of viewModel.note.getValue()
            //         is viewModel.note.setValue((java.lang.String) callbackArg_0)
            java.lang.String callbackArg_0 = androidx.databinding.adapters.TextViewBindingAdapter.getTextString(etNote);
            // localize variables for thread safety
            // viewModel.note != null
            boolean viewModelNoteJavaLangObjectNull = false;
            // viewModel.note.getValue()
            java.lang.String viewModelNoteGetValue = null;
            // viewModel
            com.mecanotun.mobile.viewmodel.BookAppointmentViewModel viewModel = mViewModel;
            // viewModel != null
            boolean viewModelJavaLangObjectNull = false;
            // viewModel.note
            androidx.lifecycle.MutableLiveData<java.lang.String> viewModelNote = null;



            viewModelJavaLangObjectNull = (viewModel) != (null);
            if (viewModelJavaLangObjectNull) {


                viewModelNote = viewModel.getNote();

                viewModelNoteJavaLangObjectNull = (viewModelNote) != (null);
                if (viewModelNoteJavaLangObjectNull) {




                    viewModelNote.setValue(((java.lang.String) (callbackArg_0)));
                }
            }
        }
    };

    public ActivityBookAppointmentBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }
    private ActivityBookAppointmentBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 2
            , (android.widget.Button) bindings[3]
            , (com.google.android.material.textfield.TextInputEditText) bindings[1]
            , (android.widget.ProgressBar) bindings[2]
            , (android.widget.Spinner) bindings[6]
            , (android.widget.Spinner) bindings[8]
            , (com.google.android.material.textfield.TextInputLayout) bindings[9]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[7]
            );
        this.btnSubmitBooking.setTag(null);
        this.etNote.setTag(null);
        this.mboundView0 = (android.widget.ScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.progressBar.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x8L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.viewModel == variableId) {
            setViewModel((com.mecanotun.mobile.viewmodel.BookAppointmentViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setViewModel(@Nullable com.mecanotun.mobile.viewmodel.BookAppointmentViewModel ViewModel) {
        this.mViewModel = ViewModel;
        synchronized(this) {
            mDirtyFlags |= 0x4L;
        }
        notifyPropertyChanged(BR.viewModel);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeViewModelNote((androidx.lifecycle.MutableLiveData<java.lang.String>) object, fieldId);
            case 1 :
                return onChangeViewModelIsLoading((androidx.lifecycle.LiveData<java.lang.Boolean>) object, fieldId);
        }
        return false;
    }
    private boolean onChangeViewModelNote(androidx.lifecycle.MutableLiveData<java.lang.String> ViewModelNote, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }
    private boolean onChangeViewModelIsLoading(androidx.lifecycle.LiveData<java.lang.Boolean> ViewModelIsLoading, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x2L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelIsLoadingGetValue = false;
        boolean androidxDatabindingViewDataBindingSafeUnboxViewModelIsLoading = false;
        int viewModelIsLoadingAndroidViewViewVISIBLEAndroidViewViewGONE = 0;
        java.lang.String viewModelNoteGetValue = null;
        java.lang.Boolean viewModelIsLoadingGetValue = null;
        androidx.lifecycle.MutableLiveData<java.lang.String> viewModelNote = null;
        androidx.lifecycle.LiveData<java.lang.Boolean> viewModelIsLoading = null;
        com.mecanotun.mobile.viewmodel.BookAppointmentViewModel viewModel = mViewModel;
        boolean ViewModelIsLoading1 = false;

        if ((dirtyFlags & 0xfL) != 0) {


            if ((dirtyFlags & 0xdL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.note
                        viewModelNote = viewModel.getNote();
                    }
                    updateLiveDataRegistration(0, viewModelNote);


                    if (viewModelNote != null) {
                        // read viewModel.note.getValue()
                        viewModelNoteGetValue = viewModelNote.getValue();
                    }
            }
            if ((dirtyFlags & 0xeL) != 0) {

                    if (viewModel != null) {
                        // read viewModel.isLoading
                        viewModelIsLoading = viewModel.isLoading();
                    }
                    updateLiveDataRegistration(1, viewModelIsLoading);


                    if (viewModelIsLoading != null) {
                        // read viewModel.isLoading.getValue()
                        viewModelIsLoadingGetValue = viewModelIsLoading.getValue();
                    }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isLoading.getValue())
                    androidxDatabindingViewDataBindingSafeUnboxViewModelIsLoadingGetValue = androidx.databinding.ViewDataBinding.safeUnbox(viewModelIsLoadingGetValue);
                if((dirtyFlags & 0xeL) != 0) {
                    if(androidxDatabindingViewDataBindingSafeUnboxViewModelIsLoadingGetValue) {
                            dirtyFlags |= 0x20L;
                    }
                    else {
                            dirtyFlags |= 0x10L;
                    }
                }


                    // read androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isLoading.getValue()) ? android.view.View.VISIBLE : android.view.View.GONE
                    viewModelIsLoadingAndroidViewViewVISIBLEAndroidViewViewGONE = ((androidxDatabindingViewDataBindingSafeUnboxViewModelIsLoadingGetValue) ? (android.view.View.VISIBLE) : (android.view.View.GONE));
                    // read !androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isLoading.getValue())
                    ViewModelIsLoading1 = !androidxDatabindingViewDataBindingSafeUnboxViewModelIsLoadingGetValue;


                    // read androidx.databinding.ViewDataBinding.safeUnbox(!androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isLoading.getValue()))
                    androidxDatabindingViewDataBindingSafeUnboxViewModelIsLoading = androidx.databinding.ViewDataBinding.safeUnbox(ViewModelIsLoading1);
            }
        }
        // batch finished
        if ((dirtyFlags & 0xeL) != 0) {
            // api target 1

            this.btnSubmitBooking.setEnabled(androidxDatabindingViewDataBindingSafeUnboxViewModelIsLoading);
            this.progressBar.setVisibility(viewModelIsLoadingAndroidViewViewVISIBLEAndroidViewViewGONE);
        }
        if ((dirtyFlags & 0xdL) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.etNote, viewModelNoteGetValue);
        }
        if ((dirtyFlags & 0x8L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setTextWatcher(this.etNote, (androidx.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.OnTextChanged)null, (androidx.databinding.adapters.TextViewBindingAdapter.AfterTextChanged)null, etNoteandroidTextAttrChanged);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): viewModel.note
        flag 1 (0x2L): viewModel.isLoading
        flag 2 (0x3L): viewModel
        flag 3 (0x4L): null
        flag 4 (0x5L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isLoading.getValue()) ? android.view.View.VISIBLE : android.view.View.GONE
        flag 5 (0x6L): androidx.databinding.ViewDataBinding.safeUnbox(viewModel.isLoading.getValue()) ? android.view.View.VISIBLE : android.view.View.GONE
    flag mapping end*/
    //end
}