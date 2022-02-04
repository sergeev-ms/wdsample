package ru.sms.wdsample.screen.sample

import io.jmix.ui.screen.*
import ru.sms.wdsample.entity.Sample

@UiController("wds_Sample.browse")
@UiDescriptor("sample-browse.xml")
@LookupComponent("samplesTable")
class SampleBrowse : StandardLookup<Sample>()