package ru.sms.wdsample.screen.user

import ru.sms.wdsample.entity.User
import io.jmix.ui.navigation.Route
import io.jmix.ui.screen.*

@UiController("wds_User.browse")
@UiDescriptor("user-browse.xml")
@LookupComponent("usersTable")
@Route("users")
open class UserBrowse : StandardLookup<User>()