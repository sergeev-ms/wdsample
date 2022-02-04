package ru.sms.wdsample.screen.sample

import io.jmix.ui.screen.*
import ru.sms.wdsample.entity.Sample

@UiController("wds_Sample.edit")
@UiDescriptor("sample-edit.xml")
@EditedEntityContainer("sampleDc")
class SampleEdit : StandardEditor<Sample>()